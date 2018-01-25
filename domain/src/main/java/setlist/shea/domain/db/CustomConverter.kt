package setlist.shea.domain.db

import android.arch.persistence.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import setlist.shea.domain.model.Song

/**
 * Created by adamshea on 1/13/18.
 */

class CustomConverter {

    @TypeConverter
    fun fromSongList(song: List<Song>): String = Gson().toJson(song)

    @TypeConverter
    fun toSongList(songList: String): List<Song> =
            Gson().fromJson(songList, object : TypeToken<List<Song>>() { }.type)
}
