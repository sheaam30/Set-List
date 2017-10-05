package setlist.shea.setlist.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R

/**
 * Created by Adam on 8/28/2017.
 */
class SongViewHolder constructor(view : View): RecyclerView.ViewHolder(view), ISongViewHolder<Song> {

    val songName : TextView = view.findViewById(R.id.song_name)

    override fun bind(song: Song) {
        songName.text = song.name
    }
}