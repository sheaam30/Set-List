package setlist.shea.setlist.redux

import setlist.shea.setlist.main.redux.MainActivityState
import setlist.shea.setlist.setlist.redux.SetListState
import setlist.shea.setlist.songlist.redux.SongListState

/**
 * Created by adamshea on 12/30/17.
 */
data class AppState(var setListState: SetListState = SetListState(),
                    var songListState: SongListState = SongListState(),
                    var mainActivityState: MainActivityState = MainActivityState())