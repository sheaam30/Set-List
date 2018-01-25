package setlist.shea.setlist.redux

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import me.tatarka.redux.middleware.Middleware
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import setlist.shea.setlist.setlist.redux.SetListActions
import setlist.shea.setlist.songlist.redux.SongListActions
import javax.inject.Inject

/**
 * Created by Adam on 1/20/2018.
 */
/**
 * Handles any actions that should result in access to the Persistence Layer
 */
class PersistenceMiddleware<A, R> @Inject constructor(private val store: AppStore, private val setListDao: SetListDao) : Middleware<A, R> {

    override fun dispatch(next: Middleware.Next<A, R>, action: A): R {
        when (action){
            is SetListActions.ListAction.Delete -> {
                async(CommonPool) { deleteSetList(store.state.setListState.setLists[action.index]) }
            }
            is SetListActions.AddSetList -> {
                async(CommonPool) { addSetList(action.setListName) }
            }
            is SongListActions.AddSongAction -> {
                async(CommonPool) { addSong(action.song) }
            }
            is SongListActions.SongMovedAction -> {
                async(CommonPool) { songMoved(action.fromIndex, action.toIndex)}
            }
            is SongListActions.SongItemCheckedAction -> {
                async(CommonPool) { songChecked(action.indexChecked) }
            }
        }
        return next.next(action)
    }

    private fun songChecked(indexChecked: Int) {
        val setList = store.state.songListState.setList?.copy()
        val songList = MutableList(setList?.songs?.size!!, { index -> setList.songs[index] } )
        val updatedChecked = !songList[indexChecked].played
        val updatedSong = songList[indexChecked].copy(played = updatedChecked)
        songList[indexChecked] = updatedSong
        setList.songs = songList
        setListDao.insertSetList(setList)
    }

    private fun songMoved(fromIndex: Int, toIndex: Int) {
        val newList = store.state.songListState.setList?.songs as MutableList
        newList.add(toIndex, newList.removeAt(fromIndex))
        val setList = store.state.songListState.setList?.copy(songs = newList)
        if (setList != null) {
            setListDao.insertSetList(setList)
        }
    }

    private fun addSong(song: Song) {
        val songState = store.state.songListState.copy()
        (songState.setList?.songs as MutableList<Song>).add(song)
        setListDao.insertSetList(songState.setList)
    }

    private fun deleteSetList(setList: SetList) {
        setListDao.delete(setList)
    }

    private fun addSetList(setListName: String) {
        setListDao.insertSetList(SetList(setListName))
    }
}