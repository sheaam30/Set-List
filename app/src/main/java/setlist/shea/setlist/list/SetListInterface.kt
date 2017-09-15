package setlist.shea.setlist.list

import com.shea.mvp.presenter.BaseInterface
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
    }

    interface ListPresenterInterface : BaseInterface.BasePresenterInterface {
        fun onAddListFabClicked()
        fun addSetList(setList : SetList)
    }
}