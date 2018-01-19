package setlist.shea.setlist.song_list.redux

import setlist.shea.domain.model.SetList
import setlist.shea.setlist.Action

/**
 * Created by adamshea on 12/31/17.
 */
sealed class SongListActions: Action {
    data class SongListUpdatedAction(val setList: SetList) : SongListActions()
    class ExportSongListClickedAction : SongListActions()
    data class Start(val setList: SetList) : SongListActions()
    class Loading : SongListActions()
    data class ReorderSongAction(val fromIndex: Long, val toIndex: Long) : SongListActions()
}