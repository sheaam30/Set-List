package setlist.shea.setlist.list.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import setlist.shea.setlist.list.add_song_dialog.AddSongCallback
import setlist.shea.setlist.list.add_song_dialog.AddSongDialog
import timber.log.Timber

/**
 * Created by Adam on 8/28/2017.
 */
class SetPresenter constructor(var setListRepository: SetListRepository, override var view: SetListContract.View) : SetListContract.Presenter {

    var disposables : CompositeDisposable = CompositeDisposable()

    override fun onSaveState(outState: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onViewsSetup() {
        val currentSetList = setListRepository.setList
        if (currentSetList == null) {
            view.showEmptyState()
        } else {
            view.showListState()
            disposables.add(setListRepository.getSongsFromSetList(currentSetList)
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
        setListRepository.addSetList(setList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        view.showListState()
    }

    override fun loadSongsFromSetList(setList: SetList) {
        setListRepository.setList = setList
        setListRepository.getSongsFromSetList(setList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ songs ->
                    view.showListState()
                    view.displaySongs(songs) },
                        { t -> Timber.e(t) })
    }

    override fun songAdded(songName: String, songArtist: String, songGenre: String) {
        setListRepository.addSongToSetList(Song(songName, songArtist, songGenre, setListRepository.setList!!))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { }, { t -> Timber.e(t)})
    }

    override fun getListActionListener(): View.OnClickListener {
        return View.OnClickListener { view ->
            val addSongDialog = AddSongDialog(view.context, object : AddSongCallback() {
                override fun addSongClicked(songName: String, songArtist: String, songGenre: String) {
                    songAdded(songName, songArtist, songGenre)
                }
            })
            addSongDialog.show()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        disposables.clear()
    }
}