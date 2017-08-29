package setlist.shea.setlist.list

import android.content.SharedPreferences
import com.shea.mvp.interactor.BaseInteractor
import io.reactivex.Flowable
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SongDao
import setlist.shea.domain.model.Song

/**
 * Created by Adam on 8/28/2017.
 */
class SetListInteractor constructor(songDao: SongDao, parser: Parser, writer: Writer, sharedPreferences: SharedPreferences): BaseInteractor() {

    val CURRENT_SET_LIST = "currentSetList"

    val songDao : SongDao
    val parser : Parser
    val writer : Writer
    val sharedPrefs : SharedPreferences

    init {
        this.songDao = songDao
        this.parser = parser
        this.writer = writer
        this.sharedPrefs = sharedPreferences
    }

    fun getCurrentSetList() : String? {
        return sharedPrefs.getString(CURRENT_SET_LIST, null)
    }

    fun getSetList(currentSetList: String) : Flowable<List<Song>> {
        return songDao.getSetList(currentSetList)
    }


}