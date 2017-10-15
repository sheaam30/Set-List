package setlist.shea.setlist.list.mvp

import android.app.Application
import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.Function
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.db.SongDao
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import setlist.shea.setlist.SetListApp
import java.io.File
import javax.inject.Inject

/**
 * Created by Adam on 8/28/2017.
 */
open class SetListRepository @Inject constructor(private val songDao: SongDao, private val setListDao: SetListDao, parser: Parser, writer: Writer, var context : Application): SetListContract.Repository {

    private val parser : Parser = parser
    private val writer : Writer = writer

    override lateinit var setList: SetList

    override fun addSetList(list: SetList) : Completable {
        return Completable.defer {
            Completable.create {  setListDao.insertSetList(list) }
        }
    }

    override fun addSongToSetList(song : Song) : Completable {
        return Completable.defer {
            Completable.create { songDao.insertSong(song) }
        }
    }

    override fun getSongsFromSetList(currentSetList: SetList) : Flowable<List<Song>> {
        return songDao.getSetList(currentSetList.listName)
    }

    override fun shareSetListFile(currentSetList : SetList) : Single<File> {
        return getSongsFromSetList(currentSetList)
                .single(emptyList())
                .flatMap { songs ->
                    val file = File(context.filesDir, "filename.csv")
                    writer.writeFile(file, songs)
                    Single.just(file)
                }
    }



}