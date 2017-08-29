package setlist.shea.setlist.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R


/**
 * Created by Adam on 8/28/2017.
 */
class RecyclerViewAdapter : RecyclerView.Adapter<SongViewHolder>() , ItemTouchHelperAdapter {

    var songs: List<Song> = ArrayList()

    override fun onBindViewHolder(holder: SongViewHolder?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent?.getContext()).inflate(R.layout.song_list_item, parent, false)
        return SongViewHolder(view)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemDismiss(position: Int) {
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}