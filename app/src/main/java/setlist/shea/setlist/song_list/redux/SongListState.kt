package setlist.shea.setlist.song_list.redux

import setlist.shea.domain.model.SetList

/**
 * Created by adamshea on 1/1/18.
 */
data class SongListState(val setList: SetList? = null,
                         var addingSong: Boolean = false,
                         var isLoading: Boolean = false,
                         var isAddSongShowing: Boolean = false,
                         var isStarting: Boolean = true)