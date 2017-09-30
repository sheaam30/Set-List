package setlist.shea.domain.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single
import setlist.shea.domain.model.SetList

/**
 * Created by adamshea on 9/15/17.
 */
@Dao
interface SetListDao {

    @Query("SELECT * FROM setlist")
    fun getAll() : Single<List<SetList>>

    @Insert
    fun insertSetList(setList: SetList)

    @Delete
    fun delete(setList: SetList)
}