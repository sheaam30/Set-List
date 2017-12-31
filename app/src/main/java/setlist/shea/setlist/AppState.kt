package setlist.shea.setlist

import setlist.shea.domain.model.SetList

/**
 * Created by adamshea on 12/30/17.
 */
sealed class AppState {

    sealed class SetListState(var setLists: List<SetList> = emptyList(), var loading: Boolean = false, var addDialog: Boolean = false) : AppState() {
        class IdleState : SetListState(emptyList(), false, false)
        class LoadingState : SetListState(emptyList(), true, false)
        class AddSetListState : SetListState(emptyList(), false, true)
        class ResultsState(setLists: List<SetList>) : SetListState(setLists, false, false)
    }
}