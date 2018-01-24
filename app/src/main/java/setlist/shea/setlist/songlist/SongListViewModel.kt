package setlist.shea.setlist.songlist

import android.content.Intent
import android.net.Uri
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.tatarka.redux.android.lifecycle.StoreViewModel
import setlist.shea.setlist.redux.Action
import setlist.shea.setlist.redux.AppState
import setlist.shea.setlist.redux.AppStore
import setlist.shea.setlist.songlist.redux.SongListActions
import setlist.shea.setlist.songlist.redux.SongListState
import timber.log.Timber
import java.io.File
import javax.inject.Inject


/**
 * Created by Adam on 8/28/2017.
 */

class SongListViewModel @Inject constructor(appStore: AppStore,
                                            private var songListRepository: SongListRepository)
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

    override fun onCleared() {
        disposable.clear()
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