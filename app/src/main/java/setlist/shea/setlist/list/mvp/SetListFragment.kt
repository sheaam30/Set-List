package setlist.shea.setlist.list.mvp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ViewSwitcher
import dagger.android.support.DaggerFragment
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R
import setlist.shea.setlist.list.adapter.RecyclerViewAdapter
import javax.inject.Inject



/**
 * Created by Adam on 8/28/2017.
 */
class SetListFragment : DaggerFragment(), SetListContract.View {

    @Inject
    lateinit var setPresenterContract: SetListContract.Presenter

    lateinit var recyclerView : RecyclerView
    lateinit var viewSwitcher : ViewSwitcher
    lateinit var adapter : RecyclerViewAdapter
    lateinit var fab : FloatingActionButton

    override val layoutId: Int
        get() = R.layout.fragment_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(setPresenterContract)
        val setListTitle = arguments?.get(SONGS_KEY)
        if (setListTitle != null && (setListTitle as String).isNotEmpty()) {
            setPresenterContract.loadSongsFromSetList(SetList(setListTitle))
        }
    }

    companion object {
        val SONGS_KEY = "songs"

        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(setList : SetList?): SetListFragment {
            val newsFragment = SetListFragment()
            val args = Bundle()
            args.putString(SONGS_KEY, setList?.toString())
            newsFragment.arguments = args
            return newsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = bind(R.id.recyclerview)
        adapter = RecyclerViewAdapter(setPresenterContract?.getListActionListener())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        viewSwitcher = bind(R.id.view_switcher)
        fab = bind(R.id.fab)
        fab.setOnClickListener { _ -> setPresenterContract?.onAddListFabClicked() }

        setPresenterContract.onViewsSetup()
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

        AlertDialog.Builder(context)
                .setView(editText)
                .setTitle(context.getString(R.string.new_setlist_dialog_title))
                .setPositiveButton(context.getString(R.string.ok), (DialogInterface.OnClickListener
                { _, _ -> setPresenterContract?.addSetList(SetList(editText.text.toString()))}))
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

    override fun showErrorState() {}

}