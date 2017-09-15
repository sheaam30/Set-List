package setlist.shea.setlist.list

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.ViewSwitcher
import com.shea.mvp.activity.BaseActivity
import com.shea.mvp.view.BaseView
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R

/**
 * Created by Adam on 8/28/2017.
 */
open class SetListView(activity: BaseActivity<*>?) : BaseView<SetListInterface.ListPresenterInterface>(activity), SetListInterface.ListViewInterface {

    lateinit var recyclerView : RecyclerView
    lateinit var viewSwitcher : ViewSwitcher
    lateinit var adapter : RecyclerViewAdapter
    lateinit var fab : FloatingActionButton

    override fun onSetupViews(savedInstanceState: Bundle?) {
        recyclerView = bind(R.id.recyclerview)
        adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewSwitcher = bind(R.id.view_switcher)
        fab = bind(R.id.fab)
        fab.setOnClickListener { v -> presenterInterface?.onAddListFabClicked() }
    }

    override fun showEmptyState() {
        viewSwitcher.displayedChild = 0
    }

    override fun showListState() {
        viewSwitcher.displayedChild = 1
    }

    override fun displaySongs(songs: List<Song>) {
        adapter.songs = songs
        adapter.notifyDataSetChanged()
    }

    override fun showAddListDialog() {
        val editText = EditText(context)

        dialogBuilder
                .setView(editText)
                .setPositiveButton("OK", (DialogInterface.OnClickListener
                    { dialogInterface, i -> presenterInterface?.addSetList(SetList(editText.))}))
                .setNegativeButton("Cancel", (DialogInterface.OnClickListener
                    { dialogInterface, i ->  }))
                .show()

    }

    override fun showErrorState() {}
}