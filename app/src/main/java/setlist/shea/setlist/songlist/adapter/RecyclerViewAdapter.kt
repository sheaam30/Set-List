package setlist.shea.setlist.songlist.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R
import android.R.attr.keySet
import android.graphics.Paint
import android.os.Bundle
import android.widget.TextView


/**
 * Created by Adam on 8/28/2017.
 */
class RecyclerViewAdapter(private val addItemFunc: () -> Unit,
                          private val reOrderClickedFunc: (viewHolder: RecyclerView.ViewHolder) -> Unit,
                          private val itemCheckedFunc: (checkedPosition: Int) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var songs: List<Song> = ArrayList()

    private val viewTypeSong = 1
    private val viewTypeAddSong = 2

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is SongViewHolder && songs.size > position) {
            holder.bind(songs[position])
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int, payloads: MutableList<Any>?) {
        if (payloads?.isNotEmpty()!!) {
            val bundle = payloads[0] as Bundle
            for (key in bundle.keySet()) {
                when (key) {
                    SongsDiffUtil.IS_CHECKED_EXTRA_KEY -> {
                        if (holder is SongViewHolder && songs.size > position) {
                            val shouldCheck = bundle.get(key) as Boolean
                            if (holder.songPlayed.isChecked != shouldCheck) {
                                holder.songPlayed.isChecked = bundle.get(key) as Boolean
                            }
                        }
                    }
                }
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == songs.size) return viewTypeAddSong
        return viewTypeSong
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            viewTypeSong -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_song_list_item, parent, false)
                val songViewHolder = SongViewHolder(view)
                songViewHolder.moveItem.setOnTouchListener { _, event ->
                    when {
                        event.action == MotionEvent.ACTION_DOWN -> {
                            reOrderClickedFunc(songViewHolder)
                            true
                        } else -> { false }
                    }
                }
                songViewHolder.songPlayed.setOnCheckedChangeListener { _, checked ->
                    //If the item gets checked when the fragment loads up, this would trigger and
                    //we don't want that.
                    if (songs[songViewHolder.adapterPosition].played != checked) {
                        songViewHolder.songName.strikeThrough(checked)
                        songViewHolder.songArtist.strikeThrough(checked)
                        itemCheckedFunc(songViewHolder.adapterPosition)
                    }
                }
                songViewHolder
            }
            viewTypeAddSong -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_add_song_list_item, parent, false)
                val viewHolder = AddSongViewHolder(view)
                viewHolder.addItem.setOnClickListener({ addItemFunc() })
                viewHolder
            }
            else -> throw IllegalArgumentException("Unknown ViewType " + viewType)
        }

    override fun getItemCount(): Int = songs.size + 1

}

fun TextView.strikeThrough(enabled: Boolean) = if (enabled) {
    paintFlags = paintFlags.plus(Paint.STRIKE_THRU_TEXT_FLAG)
} else {
    paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}