package setlist.shea.setlist.song_list.redux

import me.tatarka.redux.middleware.Middleware
import setlist.shea.domain.db.SetListDao
import setlist.shea.setlist.AppStore
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Adam on 1/20/2018.
 */
class PersistenceMiddleware<A, R> @Inject constructor(val store: AppStore, val setListDao: SetListDao) : Middleware<A, R> {

    override fun dispatch(next: Middleware.Next<A, R>, action: A): R {
        var returnAction = next.next(action)
        Timber.i("Middleware " + returnAction.toString())
        return returnAction
    }
}