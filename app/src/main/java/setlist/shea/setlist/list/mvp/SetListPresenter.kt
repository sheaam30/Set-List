package setlist.shea.setlist.list.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.shea.mvp.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import setlist.shea.setlist.list.add_song_dialog.AddSongCallback
import setlist.shea.setlist.list.add_song_dialog.AddSongDialog
import timber.log.Timber
import java.io.File


/**
 * Created by Adam on 8/28/2017.
 */

class SetListPresenter constructor(private var setListRepository: SetListContract.Repository, private var setListView: SetListContract.View)
    : BasePresenter<SetListContract.Repository, SetListContract.View>(setListRepository, setListView), SetListContract.Presenter {

    var disposables : CompositeDisposable = CompositeDisposable()

    override fun onSetupViews(savedInstanceState: Bundle?) {
        super.onSetupViews(savedInstanceState)
        if (setListRepository.setList == null) {
            setListView.showEmptyState()
        } else {
            setListView.showListState()
            disposables.add(setListRepository.getSongsFromSetList(setListRepository.setList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ songs ->
                    setListView.displaySongs(songs)
                }, {
                    _: Throwable -> setListView.showErrorState()
                }))
        }
    }

    override fun onAddListFabClicked() {
        setListView.showAddListDialog()
    }

    override fun addSetList(setList : SetList) {
        setListRepository.addSetList(setList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        setListView.showListState()
    }

    override fun loadSongsFromSetList(setList: SetList) {
        setListRepository.setList = setList
        setListRepository.getSongsFromSetList(setList)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ songs ->
            if (songs.isNotEmpty()) {
                setListView.showListState()
                setListView.displaySongs(songs)
            }
        }) { t ->
            Timber.e(t)
        }
    }

    override fun songAdded(songName: String, songArtist: String, songGenre: String) {
        setListRepository.addSongToSetList(Song(songName, songArtist, songGenre, setListRepository.setList))
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

    override fun exportClicked() {
        setListRepository.shareSetListFile(setListRepository.setList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { file ->
                    val intent = getShareIntent(file)
                    setListView.shareFileIntent(intent)
                }, {
                    Timber.e("Failure")
                })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
        disposables.clear()
    }

    override fun getShareIntent(file : File) : Intent {
        val intent = Intent()
        intent.type = "text/csv"
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+ file.path))
        intent.putExtra(Intent.EXTRA_SUBJECT,
                "Sharing File...")
        intent.putExtra(Intent.EXTRA_TEXT, "Sharing File...")
        return intent
    }
}