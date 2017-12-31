package setlist.shea.setlist.set_list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_set_list.*
import kotlinx.android.synthetic.main.add_setlist_dialog.view.*
import setlist.shea.setlist.AppState
import setlist.shea.setlist.R
import setlist.shea.setlist.R.layout.add_setlist_dialog
import setlist.shea.setlist.set_list.redux.SetListActions
import javax.inject.Inject

class SetListActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SetListAdapter
    lateinit var setListViewModel: SetListViewModel
    @Inject lateinit var setListViewModelFactory: SetListViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_list)
        setupViewModel()
        setupViews()
    }

    private fun setupViews() {
        setupRecyclerView()
        fab.setOnClickListener { _ -> setListViewModel.dispatch(SetListActions.AddSetListClickedAction()) }
    }

    private fun setupViewModel() {
        setListViewModel = ViewModelProviders.of(this, setListViewModelFactory).get(SetListViewModel::class.java)
        setListViewModel.state.observe(this, Observer { state -> onNewState(state) })
        setListViewModel.dispatch(SetListActions.StartAction())
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview)
        adapter = SetListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun onNewState(state: AppState?) {
        when (state) {
            is AppState.SetListState.IdleState -> {
                setListViewModel.fetchSetLists()
            }
            is AppState.SetListState.ResultsState -> {
                if (state.setLists.isEmpty()) renderEmptyState()
                else renderResultState(state)
            }
            is AppState.SetListState.AddSetListState -> {
                renderAddingListState(state)
            }
            is AppState.SetListState.LoadingState -> {
                //TODO Show Progress
            }
        }
    }

    private fun renderAddingListState(state: AppState.SetListState.AddSetListState) {
        val alertDialogBuilderUserInput = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(add_setlist_dialog, null)

        val alertDialog = alertDialogBuilderUserInput.setView(view)
                .setCancelable(true)
                .setPositiveButton("Add", { dialogBox, id ->
                    setListViewModel.addSetList(view.userInputDialog.text.toString())
                })
                .setNegativeButton("Cancel", { dialogBox, id ->

                })
                .create()
        alertDialog.show()
    }

    private fun renderResultState(state: AppState.SetListState.ResultsState) {
        //TODO Hide Progress
        recyclerView.visibility = View.VISIBLE
        adapter.setLists = state.setLists
        adapter.notifyDataSetChanged()
    }

    private fun renderEmptyState() {
        recyclerView.visibility = View.GONE
    }
}
