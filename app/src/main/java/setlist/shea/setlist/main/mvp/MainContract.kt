package setlist.shea.setlist.main.mvp

import android.content.DialogInterface
import com.shea.mvp.BaseContract
import io.reactivex.Single
import setlist.shea.domain.model.SetList

/**
 * Created by Adam on 6/3/2017.
 */
interface MainContract {

    interface Repository : BaseContract.Repository {
        fun getSetListTitles() : Single<List<SetList>>
        fun setCurrentSetList(setList: String)
        fun getCurrentSetList() : SetList?
    }

    interface View : BaseContract.View<Presenter> {
        fun showList(setList : SetList?)
        fun showLoadDialog(setList : List<SetList>)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadSetListTitles()
        fun loadSetList(setList: SetList)
        fun getAddSetListClickListener(setListArray: Array<String?>) : DialogInterface.OnClickListener
    }
}