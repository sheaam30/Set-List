package setlist.shea.setlist.main.mvp

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import setlist.shea.setlist.SetListApp
import javax.inject.Inject

/**
 * Created by Adam on 6/3/2017.
 */
open class MainRepository @Inject constructor(val setListDao: SetListDao, val preferences: SharedPreferences) : MainContract.Repository {

    private val CURRENT_SET_LIST = "currentSetList"

    override fun getSetListTitles() : Single<List<SetList>> {
        return setListDao.getAll()
    }

    override fun setCurrentSetList(setList: String) {
        preferences.edit().putString("currentSetList", setList).apply()
    }

    override fun getCurrentSetList() : SetList? {
        val setList = preferences.getString(CURRENT_SET_LIST, null)
        if (setList.isNullOrEmpty()) return null
        return SetList(setList)
    }

}