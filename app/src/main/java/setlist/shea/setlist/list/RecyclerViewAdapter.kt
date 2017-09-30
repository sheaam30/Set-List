package setlist.shea.setlist.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R


/**
 * Created by Adam on 8/28/2017.
 */
class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() , ItemTouchHelperAdapter {

    var songs: List<Song> = ArrayList()
    val VIEW_TYPE_SONG = 1
    val VIEW_TYPE_ADD_SONG = 2

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        if (position == songs.size) return VIEW_TYPE_ADD_SONG
        return VIEW_TYPE_SONG
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_SONG) {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.song_list_item, parent, false)
            return SongViewHolder(view)
        } else if (viewType == VIEW_TYPE_ADD_SONG) {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.add_song_list_item, parent, false)
            return SongViewHolder(view)
        } else {
            throw IllegalArgumentException("")
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemDismiss(position: Int) {
    }

    override fun getItemCount(): Int {
        return songs.size + 1
    }
}