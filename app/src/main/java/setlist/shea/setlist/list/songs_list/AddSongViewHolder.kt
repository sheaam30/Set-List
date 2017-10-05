package setlist.shea.setlist.list.songs_list

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.add_song_list_item.view.*
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R

/**
 * Created by Adam on 10/4/2017.
 */
class AddSongViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), ISongViewHolder {
    var addItem : ConstraintLayout? = itemView?.findViewById(R.id.add_item)

    override fun bind(song: Song) {
    }
}