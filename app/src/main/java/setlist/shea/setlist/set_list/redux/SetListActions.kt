package setlist.shea.setlist.set_list.redux

import setlist.shea.domain.model.SetList
import setlist.shea.setlist.Action

/**
 * Created by adamshea on 12/30/17.
 */
sealed class SetListActions: Action {
    class StartAction : SetListActions()
    class AddSetListClickedAction : SetListActions()
    data class SetListResponseAction(val setList: List<SetList>) : SetListActions()
    data class AddSetListAction(private val setList: String) : SetListActions()
}