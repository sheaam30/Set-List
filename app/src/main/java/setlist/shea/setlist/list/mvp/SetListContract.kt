package setlist.shea.setlist.list.mvp

import com.shea.mvp.presenter.BasePresenterInterface
import com.shea.mvp.view.BaseViewInterface
import io.reactivex.Flowable
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song

/**
 * Created by Adam on 8/28/2017.
 */
interface SetListContract {

    interface View : BaseViewInterface {
        fun showEmptyState()
        fun showListState()
        fun displaySongs(songs: List<Song>)
        fun showErrorState()
        fun showAddListDialog()
        fun showSetList(setList: Flowable<List<Song>>)
    }

    interface Presenter : BasePresenterInterface<View> {
        fun onAddListFabClicked()
        fun addSetList(setList : SetList)
        fun loadSongsFromSetList(setList: SetList)
        fun songAdded(songName : String, songArtist : String, songGenre : String)
        fun getListActionListener(): android.view.View.OnClickListener
    }
}