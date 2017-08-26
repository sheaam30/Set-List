package setlist.shea.domain.db

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Dao
import io.reactivex.Flowable
import setlist.shea.domain.model.Song

@Dao
interface SongDao {

    @Query("SELECT * FROM song")
    fun getAll() : Flowable<List<Song>>

    @Insert
    fun insertAll(song : Song)

    @Delete
    fun delete(song : Song)
}