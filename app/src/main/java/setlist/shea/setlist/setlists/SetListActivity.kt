package setlist.shea.setlist.setlists

import android.opengl.Visibility
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable

import kotlinx.android.synthetic.main.activity_set_list.*
import setlist.shea.setlist.R
import setlist.shea.setlist.setlists.reducers.SetListReducer
import tw.geothings.rekotlin.Action
import tw.geothings.rekotlin.Store
import tw.geothings.rekotlin.StoreSubscriber

class SetListActivity : AppCompatActivity(), StoreSubscriber<SetListViewState> {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SetListAdapter
    val setListStore = Store(SetListReducer(), SetListViewState.EmptyState())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_list)
        setupRecyclerView()
        setListStore.subscribe(this)
        setListStore.dispatch(SetListReducer.InitialLoad())
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview)
        adapter = SetListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun newState(state: SetListViewState) {
        when (state) {
            is SetListViewState.EmptyState -> {
                renderEmptyState()
            }
            is SetListViewState.ResultState -> {
                renderResultState(state)
            }
            is SetListViewState.AddingSetListState -> {
                renderAddingListState(state)
            }
        }
    }

    private fun renderAddingListState(state: SetListViewState.AddingSetListState) {
        //TODO Show Dialog to add item
    }

    private fun renderResultState(state: SetListViewState.ResultState) {
        recyclerView.visibility = View.VISIBLE
        adapter.setLists = state.setLists
        adapter.notifyDataSetChanged()
    }

    private fun renderEmptyState() {
        recyclerView.visibility = View.GONE
    }
}
