package setlist.shea.setlist.list.songs_list

import setlist.shea.domain.model.Song

/**
 * Created by Adam on 10/4/2017.
 */
interface ISongViewHolder {
    fun bind(song: Song)
}