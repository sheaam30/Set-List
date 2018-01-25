package setlist.shea.setlist.songlist.redux

import me.tatarka.redux.Reducer
import me.tatarka.redux.Reducers
import setlist.shea.setlist.redux.Action
import setlist.shea.setlist.redux.AppState
import timber.log.Timber

/**
 * Created by adamshea on 12/31/17.
 */
class SongListReducers {

    companion object {
        fun reducer(): Reducer<Action, AppState>? {
            return Reducers.matchClass<Action, AppState>()
                    .`when`(SongListActions.Start::class.java, songListStartAction())
                    .`when`(SongListActions.SongListUpdatedAction::class.java, songListResponseAction())
                    .`when`(SongListActions.Loading::class.java, songListLoadAction())
        }

        private fun songListStartAction(): Reducer<SongListActions.Start, AppState> =
                Reducer { action, state ->
                    state.copy(songListState = state.songListState.copy(setList = action.setList))
                }

        private fun songListLoadAction(): Reducer<SongListActions.Loading, AppState> =
                Reducer { _, state ->
                    state.copy(songListState = state.songListState.copy(isStarting = false, isLoading = true))
                }

        private fun songListResponseAction(): Reducer<SongListActions.SongListUpdatedAction, AppState> =
                Reducer { action, state ->
                    when {
                        action.setList.songs.isEmpty() -> {
                            state.copy(songListState = state.songListState.copy(isStarting = false, isLoading = false))
                        }
                        else -> {
                            state.copy(songListState = state.songListState.copy(isStarting = false, isLoading = false, setList = action.setList))
                        }
                    }
                }

    }
}