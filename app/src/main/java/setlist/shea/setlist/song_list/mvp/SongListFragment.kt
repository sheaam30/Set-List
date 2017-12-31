package setlist.shea.setlist.song_list.mvp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.EditText
import android.widget.ViewSwitcher
import com.shea.mvp.fragment.BaseFragment
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R
import setlist.shea.setlist.song_list.adapter.RecyclerViewAdapter
import javax.inject.Inject



/**
 * Created by Adam on 8/28/2017.
 */
open class SongListFragment : BaseFragment<SongListContract.Presenter>(), SongListContract.View {

    @Inject
    lateinit var songPresenterContract: SongListContract.Presenter

    private lateinit var recyclerView : RecyclerView
    private lateinit var viewSwitcher : ViewSwitcher
    private lateinit var adapter : RecyclerViewAdapter
    private lateinit var fab : FloatingActionButton

    override val layoutId: Int
        get() = R.layout.fragment_list

    companion object {
        val SONGS_KEY = "songs"

        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(setList : SetList?): SongListFragment {
            val newsFragment = SongListFragment()
            val args = Bundle()
            args.putString(SONGS_KEY, setList?.toString())
            newsFragment.arguments = args
            return newsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val setListTitle = arguments?.get(SONGS_KEY)
        if (setListTitle != null && (setListTitle as String).isNotEmpty()) {
            songPresenterContract.loadSongsFromSetList(SetList(setListTitle))
        }
    }

    override fun setupViews(bundle: Bundle?) {
        setHasOptionsMenu(true)
        recyclerView = bind(R.id.recyclerview)
        adapter = RecyclerViewAdapter(songPresenterContract?.getListActionListener())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        viewSwitcher = bind(R.id.view_switcher)
        fab = bind(R.id.fab)
        fab.setOnClickListener { _ -> songPresenterContract?.onAddListFabClicked() }
    }

    override fun getPresenter(): SongListContract.Presenter {
        return songPresenterContract
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.export) {
            songPresenterContract.exportClicked()
            return true
        }
        return false
    }

    override fun showAddListDialog() {
        val editText = EditText(context)

        
        AlertDialog.Builder(context)
                .setView(editText)
                .setTitle(context.getString(R.string.new_setlist_dialog_title))
                .setPositiveButton(context.getString(R.string.ok), (DialogInterface.OnClickListener
                { _, _ -> songPresenterContract.addSetList(SetList(editText.text.toString()))}))
                .setNegativeButton(context.getString(R.string.cancel), (DialogInterface.OnClickListener
                { _, _ ->  }))
                .show()
    }

    override fun showSetList(setList: Flowable<List<Song>>) {
        setList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list -> adapter.songs = list }
    }

    override fun shareFileIntent(intent: Intent) {
        startActivity(Intent.createChooser(intent, "Share File"))
    }

    override fun showErrorState() {}
}