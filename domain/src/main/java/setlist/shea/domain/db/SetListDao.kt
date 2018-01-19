package setlist.shea.domain.db

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import setlist.shea.domain.model.SetList


/**
 * Created by adamshea on 9/15/17.
 */
@Dao
interface SetListDao {

    @Query("SELECT * FROM setlist")
    fun getAll() : Single<List<SetList>>

    @Query("SELECT * FROM setlist")
    fun getAllFlowable() : Flowable<List<SetList>>

    @Query("SELECT * from setlist WHERE listName LIKE :setListName")
    fun getSetList(setListName: String): Flowable<SetList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSetList(setList: SetList)

    @Delete
    fun delete(setList: SetList)

}