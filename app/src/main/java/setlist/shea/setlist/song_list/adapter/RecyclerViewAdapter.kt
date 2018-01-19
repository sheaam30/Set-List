package setlist.shea.setlist.song_list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R
import android.view.MotionEvent
import android.support.v4.view.MotionEventCompat
import android.view.View
import android.view.View.OnTouchListener




/**
 * Created by Adam on 8/28/2017.
 */
class RecyclerViewAdapter(val addItemFunc: () -> Unit, val reOrderClickedFunc: (viewHolder: RecyclerView.ViewHolder) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var songs: List<Song> = ArrayList()

    private val VIEW_TYPE_SONG = 1
    private val VIEW_TYPE_ADD_SONG = 2

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is SongViewHolder && songs.size > position) {
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
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_song_list_item, parent, false)
                val viewHolder = SongViewHolder(view)
                viewHolder.moveItem.setOnTouchListener(OnTouchListener { v, event ->
                    false })
                // Start a drag whenever the handle view it touched
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    reOrderClickedFunc(viewHolder)
                    return true
                }
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    reOrderClickedFunc(viewHolder)
                    true
                }
                false

            }
            VIEW_TYPE_ADD_SONG -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_add_song_list_item, parent, false)
                val viewHolder = AddSongViewHolder(view)
                viewHolder.addItem.setOnClickListener({ addItemFunc() })
                viewHolder
            }
            else -> throw IllegalArgumentException("")
        }
    }

    override fun getItemCount(): Int = songs.size + 1
}