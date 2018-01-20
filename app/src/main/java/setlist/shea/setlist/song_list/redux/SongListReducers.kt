package setlist.shea.setlist.song_list.redux

import me.tatarka.redux.Reducer
import me.tatarka.redux.Reducers
import setlist.shea.setlist.Action
import setlist.shea.setlist.AppState
import timber.log.Timber
import java.util.ArrayList

/**
 * Created by adamshea on 12/31/17.
 */
class SongListReducers {

    companion object {
        fun reducer(): Reducer<Action, AppState>? {
            Timber.i("SongListReducers")
            return Reducers.matchClass<Action, AppState>()
                    .`when`(SongListActions.Start::class.java, songListStartAction())
                    .`when`(SongListActions.SongListUpdatedAction::class.java, songListResponseAction())
                    .`when`(SongListActions.Loading::class.java, songListLoadAction())
                    .`when`(SongListActions.SongItemCheckedAction::class.java, songItemCheckedAction())
        }

        private fun songListStartAction(): Reducer<SongListActions.Start, AppState> =
                Reducer { action, state ->
                    Timber.i(action.toString())
                    state.copy(songListState = state.songListState.copy(setList = action.setList))
                }

        private fun songListLoadAction(): Reducer<SongListActions.Loading, AppState> =
                Reducer { action, state ->
                    Timber.i(action.toString())
                    state.copy(songListState = state.songListState.copy(isStarting = false, isLoading = true))
                }

        private fun songListResponseAction(): Reducer<SongListActions.SongListUpdatedAction, AppState> =
                Reducer { action, state ->
                    Timber.i(action.toString())

                    when {
                        action.setList.songs.isEmpty() -> {
                            state.copy(songListState = state.songListState.copy(isStarting = false, isLoading = false))
                        } else -> {
                            state.copy(songListState = state.songListState.copy(isStarting = false, isLoading = false, setList = action.setList))
                        }
                    }
                }

        private fun songItemCheckedAction(): Reducer<SongListActions.SongItemCheckedAction, AppState> =
                Reducer { action, state ->
                    Timber.i(action.toString())

                    val setList = state.songListState.setList?.copy()
                    val songList = MutableList(setList?.songs?.size!!, { index -> setList.songs[index] } )
                    val updatedChecked = !songList[action.indexChecked].played
                    val updatedSong = songList[action.indexChecked].copy(played = updatedChecked)
                    songList[action.indexChecked] = updatedSong
                    val returnCopy = state.copy(songListState = state.songListState.copy(setList = state.songListState.setList!!.copy(songs = songList)))
                    returnCopy
                }
    }
}