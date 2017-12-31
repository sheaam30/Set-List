package setlist.shea.setlist.set_list.redux

import me.tatarka.redux.Reducer
import me.tatarka.redux.Reducers
import setlist.shea.setlist.Action
import setlist.shea.setlist.AppState


/**
 * Created by adamshea on 12/30/17.
 */
class SetListReducers {

    companion object {
        fun reducer(): Reducer<Action, AppState>? {
            return Reducers.matchClass<Action, AppState>()
                    .`when`(SetListActions.StartAction::class.java, startAction())
                    .`when`(SetListActions.AddSetListClickedAction::class.java, addSetListClickedAction())
                    .`when`(SetListActions.SetListResponseAction::class.java, setListResponseAction())
                    .`when`(SetListActions.AddSetListAction::class.java, addSetListAction())
        }

        private fun addSetListAction() : Reducer<SetListActions.AddSetListAction, AppState> =
                Reducer { action, _ -> AppState.SetListState.AddSetListState() }

        private fun setListResponseAction(): Reducer<SetListActions.SetListResponseAction, AppState> =
                Reducer { action: SetListActions.SetListResponseAction, _: AppState ->  AppState.SetListState.ResultsState(action.setList) }

        private fun startAction() : Reducer<Action, AppState> =
                Reducer { _, _ -> AppState.SetListState.IdleState() }

        private fun addSetListClickedAction() : Reducer<Action, AppState> =
                Reducer { _, _ -> AppState.SetListState.AddSetListState() }
    }
}