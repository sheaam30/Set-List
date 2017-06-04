package s.shea.domain.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import s.shea.domain.model.Song

@Dao
interface SongDao {

    @Query("SELECT * FROM song")
    fun getAll() : List<Song>

    @Query("SELECT * FROM user WHERE uid IN (:setList)")
    fun loadAllBySetList(setList : String) : List<Song>

    @Insert
    fun insertAll(song : Song)

    @Delete
    fun delete(song : Song)
}