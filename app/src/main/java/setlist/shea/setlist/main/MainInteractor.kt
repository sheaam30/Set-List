package setlist.shea.setlist.main

import io.reactivex.Flowable
import setlist.shea.domain.csv.ParserInterface
import setlist.shea.domain.csv.WriterInterface
import setlist.shea.domain.db.SongDao
import setlist.shea.domain.model.Song
import javax.inject.Inject

/**
 * Created by Adam on 6/3/2017.
 */
class MainInteractor @Inject constructor(val songDao: SongDao, val parser: ParserInterface, val writer: WriterInterface) : com.shea.mvp.interactor.BaseInteractor() {

    fun importSetList(path : String) : io.reactivex.Single<List<Song>> {
        return io.reactivex.Single.defer {
            val inputStream : java.io.InputStream? = setlist.shea.setlist.SetListApp.Companion.context?.assets?.open(path)
            io.reactivex.Single.just(parser.readFile(inputStream))
        }
    }

    fun exportSetList(setList : List<Song>) : io.reactivex.Completable {
        return io.reactivex.Completable.defer {
            val file = java.io.File(setlist.shea.setlist.SetListApp.Companion.context?.getFilesDir(), "filename.csv")
            writer.writeFile(file, setList)
            io.reactivex.Completable.complete()
        }
    }

    fun loadSetList() : Flowable<List<Song>> {
        return songDao.getAll()
    }

    fun saveSetList(setList: List<Song>) {
        //TODO Save to Dao
    }
}