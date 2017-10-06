package setlist.shea.setlist.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R

/**
 * Created by Adam on 8/28/2017.
 */
class SongViewHolder constructor(view : View): RecyclerView.ViewHolder(view), SongViewHolderInterface<Song> {

    private val songName : TextView = view.findViewById(R.id.song_name)
    private val songArtist : TextView = view.findViewById(R.id.song_artist)
    val removeSong : ImageView = view.findViewById(R.id.remove_song)
    private val songPlayed : CheckBox = view.findViewById(R.id.played_checkbox)

    override fun bind(data: Song) {
        songName.text = data.name
        songArtist.text = data.artist
    }
}