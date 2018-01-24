package setlist.shea.setlist.songlist.redux

import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import setlist.shea.setlist.redux.Action

/**
 * Created by adamshea on 12/31/17.
 */
sealed class SongListActions: Action {
    data class SongListUpdatedAction(val setList: SetList) : SongListActions()
    class ExportSongListClickedAction : SongListActions()
    data class Start(val setList: SetList) : SongListActions()
    class Loading : SongListActions()
    data class SongItemCheckedAction(val indexChecked: Int) : SongListActions()
    data class AddSongAction(val song: Song) : SongListActions()
    data class SongMovedAction(val fromIndex: Int, val toIndex: Int) : SongListActions()
}