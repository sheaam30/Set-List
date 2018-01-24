package setlist.shea.setlist.songlist.adapter

import android.os.Bundle
import android.support.v7.util.DiffUtil
import setlist.shea.domain.model.Song

/**
 * Created by Adam on 1/20/2018.
 */
class SongsDiffUtil(val oldSongs: List<Song>, val newSongs: List<Song>): DiffUtil.Callback() {

    companion object {
        const val IS_CHECKED_EXTRA_KEY = "IsItemChecked"
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldSongs[oldItemPosition].name == newSongs[newItemPosition].name

    override fun getOldListSize(): Int = oldSongs.size

    override fun getNewListSize(): Int = newSongs.size

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldSong = oldSongs[oldItemPosition]
        val newSong = newSongs[newItemPosition]
        val bundle = Bundle()

        //If the played variable changed then alert this change
        if (oldSong.played != newSong.played) {
            bundle.putBoolean(IS_CHECKED_EXTRA_KEY, newSong.played)
        }

        return bundle
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldSongs[oldItemPosition] == newSongs[newItemPosition]
}