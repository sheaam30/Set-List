package setlist.shea.setlist

import setlist.shea.setlist.set_list.redux.SetListState
import setlist.shea.setlist.song_list.redux.SongListState

/**
 * Created by adamshea on 12/30/17.
 */
data class AppState(var setListState: SetListState = SetListState(), var songListState: SongListState = SongListState())