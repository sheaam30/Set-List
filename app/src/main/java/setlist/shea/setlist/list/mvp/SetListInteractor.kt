package setlist.shea.setlist.list.mvp

import android.content.SharedPreferences
import com.shea.mvp.interactor.BaseInteractor
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
class SetListInteractor @Inject constructor(songDao: SongDao, setListDao: SetListDao, parser: Parser, writer: Writer, sharedPreferences: SharedPreferences): BaseInteractor() {

    private val CURRENT_SET_LIST = "currentSetList"

    private val songDao : SongDao = songDao
    private val setListDao : SetListDao = setListDao
    private val parser : Parser = parser
    private val writer : Writer = writer
    private val sharedPrefs : SharedPreferences = sharedPreferences

    fun addSetList(list: SetList) : Completable {
        return Completable.defer {
            Completable.create {  setListDao.insertSetList(list) }
        }
    }

    fun getCurrentSetList() : String? {
        return sharedPrefs.getString(CURRENT_SET_LIST, null)
    }

    fun getSongsFromSetList(currentSetList: SetList) : Flowable<List<Song>> {
//        var songList : MutableList<Song> = ArrayList<Song>()
//        songList.add(Song("Test", "test", false, "Genre", SetList("List:")))
//        songList.add(Song("Test", "test", false, "Genre", SetList("List:")))
//        songList.add(Song("Test", "test", false, "Genre", SetList("List:")))
//        songList.add(Song("Test", "test", false, "Genre", SetList("List:")))
//        songList.add(Song("Test", "test", false, "Genre", SetList("List:")))
//        return Flowable.fromArray(songList)
        return songDao.getSetList(currentSetList.listName)
    }
}