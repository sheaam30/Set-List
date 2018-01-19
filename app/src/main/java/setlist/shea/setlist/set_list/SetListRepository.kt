package setlist.shea.setlist.set_list

import android.support.annotation.WorkerThread
import io.reactivex.Completable
import io.reactivex.Flowable
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.model.SetList
import javax.inject.Inject

/**
 * Created by adamshea on 12/30/17.
 */
class SetListRepository @Inject constructor(private var setListDao: SetListDao) {

    fun setList() : Flowable<List<SetList>> = setListDao.getAllFlowable()

    @WorkerThread
    fun addSetList(setList: SetList) : Completable =
            Completable.fromAction {  setListDao.insertSetList(setList) }

    fun deleteSetList(setList: SetList): Completable =
            Completable.fromAction { setListDao.delete(setList) }

}