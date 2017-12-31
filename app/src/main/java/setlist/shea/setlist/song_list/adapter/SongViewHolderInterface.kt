package setlist.shea.setlist.song_list.adapter

/**
 * Created by Adam on 10/4/2017.
 */
interface SongViewHolderInterface<in D> {
    fun bind(data: D)
}