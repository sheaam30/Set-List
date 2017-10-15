package setlist.shea.setlist.list.mvp

import android.content.Intent
import com.shea.mvp.BaseContract
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import java.io.File


/**
 * Created by Adam on 8/28/2017.
 */
interface SetListContract {

    interface Repository : BaseContract.Repository {
        fun addSetList(list: SetList) : Completable
        fun addSongToSetList(song : Song) : Completable
        fun getSongsFromSetList(currentSetList: SetList) : Flowable<List<Song>>
        var setList : SetList
        fun shareSetListFile(currentSetList: SetList): Single<File>
    }

    interface View : BaseContract.View<Presenter> {
        fun showEmptyState()
        fun showListState()
        fun displaySongs(songs: List<Song>)
        fun showErrorState()
        fun showAddListDialog()
        fun showSetList(setList: Flowable<List<Song>>)
        //Don't like to do nav stuff in the view
        fun shareFileIntent(intent : Intent)
    }

    interface Presenter : BaseContract.Presenter {
        fun onAddListFabClicked()
        fun addSetList(setList : SetList)
        fun loadSongsFromSetList(setList: SetList)
        fun songAdded(songName : String, songArtist : String, songGenre : String)
        fun getListActionListener(): android.view.View.OnClickListener
        fun exportClicked()
    }
}