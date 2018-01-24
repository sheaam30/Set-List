package setlist.shea.setlist.songlist

import android.app.Application
import io.reactivex.Flowable
import io.reactivex.Single
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.model.SetList
import java.io.File
import javax.inject.Inject

/**
 * Created by Adam on 8/28/2017.
 */
open class SongListRepository @Inject constructor(private val setListDao: SetListDao,
                                                  parser: Parser, writer: Writer,
                                                  var context : Application){

    private val parser : Parser = parser
    private val writer : Writer = writer

    fun getSetList(listName: String): Flowable<SetList> = setListDao.getSetList(listName)

    fun shareSetListFile(currentSetList : SetList) : Single<File> {
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