package setlist.shea.setlist.song_list.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R

/**
 * Created by Adam on 10/4/2017.
 */
class AddSongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), SongViewHolderInterface<Song> {
    var addItem : ConstraintLayout = itemView.findViewById(R.id.add_item)

    override fun bind(data: Song) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}