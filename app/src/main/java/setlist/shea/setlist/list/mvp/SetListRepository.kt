package setlist.shea.setlist.list.mvp

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Flowable
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.db.SongDao
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import javax.inject.Inject

/**
 * Created by Adam on 8/28/2017.
 */
class SetListRepository @Inject constructor(songDao: SongDao, setListDao: SetListDao, parser: Parser, writer: Writer, sharedPreferences: SharedPreferences): SetListContract.Repository {

    private val songDao : SongDao = songDao
    private val setListDao : SetListDao = setListDao
    private val parser : Parser = parser
    private val writer : Writer = writer
    private val sharedPrefs : SharedPreferences = sharedPreferences

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
}