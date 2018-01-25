package setlist.shea.setlist.setlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.add_setlist_dialog.view.*
import kotlinx.android.synthetic.main.fragment_set_list.*
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.R
import setlist.shea.setlist.R.layout.add_setlist_dialog
import setlist.shea.setlist.R.layout.fragment_set_list
import setlist.shea.setlist.setlist.redux.SetListActions
import setlist.shea.setlist.setlist.redux.SetListState
import setlist.shea.setlist.songlist.SongListFragment
import timber.log.Timber
import javax.inject.Inject


class SetListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SetListAdapter
    private lateinit var setListViewModel: SetListViewModel
    @Inject lateinit var setListViewModelFactory: SetListViewModelFactory

    companion object {
        @JvmStatic
        fun newInstance(): SetListFragment = SetListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(fragment_set_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupViews(view!!)
    }

    private fun setupViews(view: View) {
        (activity as AppCompatActivity).supportActionBar?.title = "SetLists"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setupRecyclerView(view)
        fab.setOnClickListener { _ -> showAddSetListDialog() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupViewModel() {
        setListViewModel = ViewModelProviders.of(this, setListViewModelFactory).get(SetListViewModel::class.java)
        setListViewModel.state.observe(this, Observer { state -> onNewState(state!!.setListState)  })
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerview)
        adapter = SetListAdapter( { setList, listAction ->
            when (listAction) {
                is SetListActions.ListAction.Click -> setListClicked(setList)
                is SetListActions.ListAction.Delete -> setListViewModel.dispatch(listAction)
            } } )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setListClicked(setList: SetList) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.slide_from_right,
                        R.animator.slide_to_left, R.animator.slide_from_left, R.animator.slide_to_right)
                .replace(R.id.content, SongListFragment.newInstance(setList))
                .addToBackStack(null)
                .commit()
    }

    private fun onNewState(state: SetListState) {
        if (state.isStarting) {
            setListViewModel.fetchSetLists()
        }
        if (state.setLists.isNotEmpty()) {
            renderResultState(state.setLists)
        } else {
            renderEmptyState()
        }
    }

    private fun showAddSetListDialog() {
        val alertDialogBuilderUserInput = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(add_setlist_dialog, null)

        val alertDialog = alertDialogBuilderUserInput.setView(view)
                .setCancelable(true)
                .setPositiveButton("Add", { _, _ ->
                    setListViewModel.dispatch(SetListActions.AddSetList(view.userInputDialog.text.toString()))
                })
                .setNegativeButton("Cancel", { _, _ -> })
                .create()
        alertDialog.show()
    }

    private fun renderResultState(setLists: List<SetList>) {
        recyclerView.visibility = View.VISIBLE
        adapter.setLists = setLists
        adapter.notifyDataSetChanged()
    }

    private fun renderEmptyState() {
        recyclerView.visibility = View.GONE
    }
}
