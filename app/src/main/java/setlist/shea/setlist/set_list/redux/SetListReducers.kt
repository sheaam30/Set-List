package setlist.shea.setlist.set_list.redux

import me.tatarka.redux.Reducer
import me.tatarka.redux.Reducers
import setlist.shea.setlist.Action
import setlist.shea.setlist.AppState
import timber.log.Timber


/**
 * Created by adamshea on 12/30/17.
 */
class SetListReducers {

    companion object {
        fun reducer(): Reducer<Action, AppState>? {
            Timber.i("SetListReducing")
            return Reducers.matchClass<Action, AppState>()
                    .`when`(SetListActions.Updated::class.java, setListResponseAction())
        }

        private fun setListResponseAction(): Reducer<SetListActions.Updated, AppState> =
                Reducer { action: SetListActions.Updated, state: AppState ->
                    Timber.i(action.toString())

                    state.copy(setListState = state.setListState.copy(setLists = action.setList, isStarting = false, isAddDialogShowing = false))
                }

        private fun addSetListClickedAction() : Reducer<Action, AppState> =
                Reducer { action, state ->
                    Timber.i(action.toString())

                    state.copy(setListState = state.setListState.copy(isAddDialogShowing = true))
                }
    }
}