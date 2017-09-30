package setlist.shea.setlist.list

import com.shea.mvp.presenter.BaseInterface
import io.reactivex.Flowable
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song

/**
 * Created by Adam on 8/28/2017.
 */
interface SetListInterface {

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
    }
}