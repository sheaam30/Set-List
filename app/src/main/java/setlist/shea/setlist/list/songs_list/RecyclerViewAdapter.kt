package setlist.shea.setlist.list.songs_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R
import timber.log.Timber


/**
 * Created by Adam on 8/28/2017.
 */
class RecyclerViewAdapter(val clickListener: View.OnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() , ItemTouchHelperAdapter {

    var songs: List<Song> = ArrayList()

    val VIEW_TYPE_SONG = 1
    val VIEW_TYPE_ADD_SONG = 2

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ISongViewHolder && songs.size > position) {
            holder.bind(songs[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == songs.size) return VIEW_TYPE_ADD_SONG
        return VIEW_TYPE_SONG
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SONG -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.song_list_item, parent, false)
                SongViewHolder(view)
            }
            VIEW_TYPE_ADD_SONG -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.add_song_list_item, parent, false)
                val viewHolder = AddSongViewHolder(view)
                viewHolder.addItem?.setOnClickListener(clickListener)
                viewHolder
            }
            else -> throw IllegalArgumentException("")
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemDismiss(position: Int) {
    }

    override fun getItemCount(): Int = songs.size + 1
}