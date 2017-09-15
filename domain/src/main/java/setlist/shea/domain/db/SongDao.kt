package setlist.shea.domain.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import setlist.shea.domain.model.Song

@Dao
interface SongDao {

    @Query("SELECT * FROM song")
    fun getAll() : Flowable<List<Song>>

    @Query("SELECT * FROM song WHERE listName = :setList")
    fun getSetList(setList : String) : Flowable<List<Song>>

    @Insert
    fun insertSong(song : Song)

    @Delete
    fun delete(song : Song)
}