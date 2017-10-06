package setlist.shea.setlist.main.mvp

import android.content.DialogInterface
import com.shea.mvp.presenter.BaseInterface
import setlist.shea.domain.model.SetList

/**
 * Created by Adam on 6/3/2017.
 */
interface MainContract {

    interface MainViewInterface : BaseInterface.BaseViewInterface {
        fun showList(setList : SetList?)
        fun showLoadDialog(setList : List<SetList>)
    }

    interface MainPresenterInterface : BaseInterface.BasePresenterInterface {
        fun loadSetListTitles()
        fun loadSetList(setList: SetList)
        fun getAddSetListClickListener(setListArray: Array<String?>) : DialogInterface.OnClickListener
    }
}