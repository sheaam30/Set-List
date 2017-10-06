package setlist.shea.setlist.list.mvp

import android.view.View
import com.shea.mvp.presenter.BaseInterface
import io.reactivex.Flowable
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song

/**
 * Created by Adam on 8/28/2017.
 */
interface SetListContract {

    interface ListViewInterface : BaseInterface.BaseViewInterface {
        fun showEmptyState()
        fun showListState()
        fun displaySongs(songs: List<Song>)
        fun showErrorState()
        fun showAddListDialog()
        fun showSetList(setList: Flowable<List<Song>>)
    }

    interface ListPresenterInterface : BaseInterface.BasePresenterInterface {
        fun onAddListFabClicked()
        fun addSetList(setList : SetList)
        fun loadSongsFromSetList(setList: SetList)
        fun songAdded(songName : String, songArtist : String, songGenre : String)
        fun getListActionListener(): View.OnClickListener
    }
}