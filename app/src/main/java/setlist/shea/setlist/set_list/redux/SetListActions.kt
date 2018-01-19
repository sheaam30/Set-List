package setlist.shea.setlist.set_list.redux

import setlist.shea.domain.model.SetList
import setlist.shea.setlist.Action

/**
 * Created by adamshea on 12/30/17.
 */
sealed class SetListActions: Action {
    class Start : SetListActions()
    class Loading: SetListActions()
    data class Updated(val setList: List<SetList>) : SetListActions()
}