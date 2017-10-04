package setlist.shea.setlist.main.mvp

import com.shea.mvp.interactor.BaseInteractor
import io.reactivex.Single
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.model.SetList
import javax.inject.Inject

/**
 * Created by Adam on 6/3/2017.
 */
class MainInteractor @Inject constructor(val setListDao: SetListDao) : BaseInteractor() {

//    fun importSetList(path : String) : io.reactivex.Single<List<Song>> {
//        return io.reactivex.Single.defer {
//            val inputStream : java.io.InputStream? = SetListApp.Companion.context?.assets?.open(path)
//            Single.just(parser.readFile(inputStream))
//        }
//    }
//
//    fun exportSetList(setList : List<Song>) : io.reactivex.Completable {
//        return io.reactivex.Completable.defer {
//            val file = java.io.File(setlist.shea.setlist.SetListApp.Companion.context?.getFilesDir(), "filename.csv")
//            writer.writeFile(file, setList)
//            io.reactivex.Completable.complete()
//        }
//    }

    fun getSetListTitles() : Single<List<SetList>> {
        return setListDao.getAll()
    }

}