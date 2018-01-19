package setlist.shea.setlist.song_list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_song_list.*
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R
import setlist.shea.setlist.song_list.adapter.CallbackItemTouch
import setlist.shea.setlist.song_list.adapter.MyItemTouchHelperCallback
import setlist.shea.setlist.song_list.adapter.RecyclerViewAdapter
import setlist.shea.setlist.song_list.add_song_dialog.AddSongDialog
import setlist.shea.setlist.song_list.redux.SongListActions
import setlist.shea.setlist.song_list.redux.SongListState
import javax.inject.Inject




/**
 * Created by Adam on 8/28/2017.
 */
class SongListFragment : DaggerFragment(), CallbackItemTouch {

    private lateinit var adapter : RecyclerViewAdapter
    private lateinit var songListViewModel : SongListViewModel
    @Inject lateinit var songListViewModelFactory: SongListViewModelFactory

    private val callback = MyItemTouchHelperCallback(this)
    private val touchHelper = ItemTouchHelper(callback)

    companion object {
        val SETS_KEY = "songs"

        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(setList: SetList): SongListFragment {
            val newsFragment = SongListFragment()
            val args = Bundle()
            args.putParcelable(SETS_KEY, setList)
            newsFragment.arguments = args
            return newsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_song_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val setList = (arguments?.get(SETS_KEY) as SetList)

        setupViews(savedInstanceState, setList)
        setupViewModel()

        songListViewModel.dispatchAction(SongListActions.Start(setList))
    }

    fun setupViews(bundle: Bundle?, setList: SetList) {
        setHasOptionsMenu(false)
        (activity as AppCompatActivity).supportActionBar?.title = setList.listName
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        adapter = RecyclerViewAdapter( { showAddSongDialog() }, { viewHolder -> touchHelper.startDrag(viewHolder) } )
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter
        touchHelper.attachToRecyclerView(recyclerview)
    }

    private fun setupViewModel() {
        songListViewModel = ViewModelProviders.of(this, songListViewModelFactory).get(SongListViewModel::class.java)
        songListViewModel.state.observe(this, Observer { state -> state?.songListState?.let { onNewState(it) } })
    }

    private fun onNewState(state: SongListState) {
        when {
            state.isStarting -> state.setList?.listName?.let { songListViewModel.fetchSetList(it) }
            state.isLoading -> showLoadingState()
            state.setList != null -> {
                showListState(state.setList.songs)
            }
        }
    }

    private fun showLoadingState() {
        loading_bar.visibility = View.VISIBLE
        recyclerview.visibility = View.GONE
    }

    private fun showAddSongDialog() {
        val addSongDialog = AddSongDialog(context,
                { name, artist, genre ->
                    songListViewModel.addSong(Song(name, artist ,genre))
                })
        addSongDialog.show()
    }

    fun showListState(songs: List<Song>) {
        loading_bar.visibility = View.GONE
        recyclerview.visibility = View.VISIBLE
        adapter.songs = songs
        adapter.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.export) {
            songListViewModel.dispatchAction(SongListActions.ExportSongListClickedAction())
            return true
        }
        return false
    }

    fun shareFileIntent(intent: Intent) {
        startActivity(Intent.createChooser(intent, "Share File"))
    }

    fun showErrorState() {}

    override fun itemTouchOnMove(oldPosition: Int, newPosition: Int) {
//        songListViewModel.onSongsMoved(oldPosition, newPosition)
        adapter.notifyItemMoved(oldPosition, newPosition)
    }

    override fun onItemDropped(oldPosition: Int, newPosition: Int) {
        songListViewModel.onSongsMoved(oldPosition, newPosition)
    }
}