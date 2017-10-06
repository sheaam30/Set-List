package setlist.shea.setlist.list.mvp

import android.os.Bundle
import com.shea.mvp.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import timber.log.Timber

/**
 * Created by Adam on 8/28/2017.
 */
class SetListPresenter constructor(setListInteractor: SetListInteractor, view: SetListInterface.ListViewInterface) : BasePresenter<SetListInteractor, SetListInterface.ListViewInterface>(setListInteractor, view), SetListInterface.ListPresenterInterface {

    var disposables : CompositeDisposable = CompositeDisposable()

    override fun onSetupViews(savedInstanceState: Bundle?) {
        val currentSetList = interactor.setList
        if (currentSetList == null) {
            view.showEmptyState()
        } else {
            view.showListState()
            disposables.add(interactor.getSongsFromSetList(currentSetList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ songs ->
                        view.displaySongs(songs)
                    }, {
                        _: Throwable -> view.showErrorState()
                    }))
        }
    }

    override fun onAddListFabClicked() {
        view.showAddListDialog()
    }

    override fun addSetList(setList : SetList) {
        interactor.addSetList(setList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        view.showListState()
    }

    override fun loadSongsFromSetList(setList: SetList) {
        interactor.setList = setList
        interactor.getSongsFromSetList(setList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ songs ->
                    view.showListState()
                    view.displaySongs(songs) },
                        { t -> Timber.e(t) })
    }

    override fun songAdded(songName: String, songArtist: String, songGenre: String) {
        interactor.addSongToSetList(Song(songName, songArtist, songGenre, interactor.setList!!))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { }, { t -> Timber.e(t)})
    }

    override fun onPause() {
        super.onPause()
        disposables.clear()
    }
}