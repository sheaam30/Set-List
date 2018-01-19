package setlist.shea.setlist.song_list

import android.content.Intent
import android.net.Uri
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.tatarka.redux.android.lifecycle.StoreViewModel
import setlist.shea.domain.model.Song
import setlist.shea.setlist.Action
import setlist.shea.setlist.AppState
import setlist.shea.setlist.AppStore
import setlist.shea.setlist.song_list.redux.SongListActions
import setlist.shea.setlist.song_list.redux.SongListState
import java.io.File
import javax.inject.Inject


/**
 * Created by Adam on 8/28/2017.
 */

class SongListViewModel @Inject constructor(appStore: AppStore,
                                            private var songListRepository: SongListContract.Repository)
    : StoreViewModel<AppState, AppStore>(appStore) {

    private var disposable = CompositeDisposable()

    //Clear the state every time a new Song List is instantiated
    init {
        appStore.state.songListState = SongListState()
    }

    fun dispatchAction(action: Action) {
        store.dispatch(action)
    }

    fun fetchSetList(setListName: String) {
        disposable.add(songListRepository.getSetList(setListName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ setList ->
                    dispatchAction(SongListActions.SongListUpdatedAction(setList)) }, { }))
    }

    fun addSong(song: Song) {
        val songState = store.state.songListState.copy()
        (songState.setList?.songs as MutableList<Song>).add(song)
        songListRepository.updateSetList(songState.setList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {  }
    }

    override fun onCleared() {
        disposable.clear()
    }

    fun onSongsMoved(fromPosition: Int, toPosition: Int) {
        val newList = store.state.songListState.setList?.songs as MutableList
        newList.add(toPosition, newList.removeAt(fromPosition))
        store.state.songListState.setList?.copy(songs = newList)?.let { songListRepository.updateSetList(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {})}
    }

    //
//    fun exportClicked() {
//        songListRepository.shareSetListFile(songListRepository.setList)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe( { file ->
//                    val intent = getShareIntent(file)
////                    songListView.shareFileIntent(intent)
//                }, {
//                    Timber.e("Failure")
//                })
//    }

    fun getShareIntent(file : File) : Intent {
        val intent = Intent()
        intent.type = "text/csv"
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+ file.path))
        intent.putExtra(Intent.EXTRA_SUBJECT,
                "Sharing File...")
        intent.putExtra(Intent.EXTRA_TEXT, "Sharing File...")
        return intent
    }
}