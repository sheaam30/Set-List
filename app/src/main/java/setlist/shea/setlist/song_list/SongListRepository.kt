package setlist.shea.setlist.song_list

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.model.SetList
import timber.log.Timber
import java.io.File
import javax.inject.Inject

/**
 * Created by Adam on 8/28/2017.
 */
open class SongListRepository @Inject constructor(private val setListDao: SetListDao,
                                                  parser: Parser, writer: Writer,
                                                  var context : Application) :
        SongListContract.Repository {

    private val parser : Parser = parser
    private val writer : Writer = writer

    override fun getSetList(listName: String): Flowable<SetList> = setListDao.getSetList(listName)

    override fun updateSetList(setList: SetList) : Completable = Completable.fromAction {
                Timber.i("Completable")
                setListDao.insertSetList(setList)
    }

    override fun shareSetListFile(currentSetList : SetList) : Single<File> {
//        return getSongsFromSetList(currentSetList)
//                .single(emptyList())
//                .flatMap { songs ->
//                    val file = File(context.filesDir, "filename.csv")
//                    writer.writeFile(file, songs)
//                    Single.just(file)
//                }
        return Single.just(null)
    }
}