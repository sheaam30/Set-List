package setlist.shea.setlist.list.mvp

import com.shea.mvp.presenter.BaseInterface
import io.reactivex.Flowable
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song

/**
 * Created by Adam on 8/28/2017.
 */
interface SetListContract {

    interface View : BaseInterface.BaseViewInterface {
        fun showEmptyState()
        fun showListState()
        fun displaySongs(songs: List<Song>)
        fun showErrorState()
        fun showAddListDialog()
        fun showSetList(setList: Flowable<List<Song>>)
    }

    interface Presenter : BaseInterface.BasePresenterInterface {
        fun onAddListFabClicked()
        fun addSetList(setList : SetList)
        fun loadSongsFromSetList(setList: SetList)
        fun songAdded(songName : String, songArtist : String, songGenre : String)
        fun getListActionListener(): android.view.View.OnClickListener.OnClickListener
    }
}