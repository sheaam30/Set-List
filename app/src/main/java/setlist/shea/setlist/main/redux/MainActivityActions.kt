package setlist.shea.setlist.main.redux

import setlist.shea.setlist.redux.Action

/**
 * Created by Adam on 1/23/2018.
 */
sealed class MainActivityActions : Action {
    class AddSetListDialog : MainActivityActions()
}