package setlist.shea.setlist.setlist.redux

import setlist.shea.domain.model.SetList
import setlist.shea.setlist.redux.Action

/**
 * Created by adamshea on 12/30/17.
 */
sealed class SetListActions: Action {
    class Start : SetListActions()
    class Loading: SetListActions()
    data class AddSetList(val setListName: String) : SetListActions()
    data class Updated(val setList: List<SetList>) : SetListActions()

    sealed class ListAction: SetListActions() {
        class Delete(var index: Int) : ListAction()
        class Click : ListAction()
    }
}