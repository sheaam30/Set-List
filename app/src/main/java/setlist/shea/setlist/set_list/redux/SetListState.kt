package setlist.shea.setlist.set_list.redux

import setlist.shea.domain.model.SetList

/**
 * Created by adamshea on 1/1/18.
 */

data class SetListState(var setLists: List<SetList> = emptyList(),
                        var isLoading: Boolean = false,
                        var isAddDialogShowing: Boolean = false,
                        var isStarting: Boolean = true)