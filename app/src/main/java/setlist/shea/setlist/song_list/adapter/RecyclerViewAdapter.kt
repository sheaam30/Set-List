package setlist.shea.setlist.song_list.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import android.view.ViewGroup
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R
import timber.log.Timber
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG




/**
 * Created by Adam on 8/28/2017.
 */
class RecyclerViewAdapter
    : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private val addItemFunc: () -> Unit
    private val reOrderClickedFunc: (viewHolder: RecyclerView.ViewHolder) -> Unit
    val itemCheckedFunc: (checkedPosition: Int) -> Unit

    constructor(addItemFunc: () -> Unit, reOrderClickedFunc: (viewHolder: RecyclerView.ViewHolder) -> Unit, itemCheckedFunc: (checkedPosition: Int) -> Unit) : super() {
        this.addItemFunc = addItemFunc
        this.reOrderClickedFunc = reOrderClickedFunc
        this.itemCheckedFunc = itemCheckedFunc
        this.songs = ArrayList()
    }

    var songs: List<Song>

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

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VIEW_TYPE_SONG -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_song_list_item, parent, false)
                val songViewHolder = SongViewHolder(view)
                songViewHolder.moveItem.setOnTouchListener { v, event ->
                    when {
                        event.action == MotionEvent.ACTION_DOWN -> {
                            reOrderClickedFunc(songViewHolder)
                            true
                        } else -> { false }
                    }
                }
                songViewHolder.songPlayed.setOnCheckedChangeListener { buttonView, isChecked ->
                    itemCheckedFunc(songViewHolder.adapterPosition)
                }
                songViewHolder
            }
            VIEW_TYPE_ADD_SONG -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_add_song_list_item, parent, false)
                val viewHolder = AddSongViewHolder(view)
                viewHolder.addItem.setOnClickListener({ addItemFunc() })
                viewHolder
            }
            else -> throw IllegalArgumentException("Unknown ViewType " + viewType)
        }

    override fun getItemCount(): Int = songs.size + 1
}