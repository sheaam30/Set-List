package setlist.shea.setlist

import me.tatarka.redux.Dispatcher
import me.tatarka.redux.Reducers
import me.tatarka.redux.SimpleStore
import me.tatarka.redux.android.LogMiddleware
import setlist.shea.setlist.set_list.redux.SetListReducers


/**
 * Created by adamshea on 12/30/17.
 */
class AppStore : SimpleStore<AppState>(AppState.SetListState.LoadingState()) {

    private var dispatcher: Dispatcher<Action, Action> = Dispatcher.forStore(this,
             Reducers.all(
                     SetListReducers.reducer()
             )).chain(LogMiddleware<Action, Action>("ACTION"))

    fun dispatch(action: Action): Action = dispatcher.dispatch(action)
}