package setlist.shea.setlist.main.mvp

import android.content.DialogInterface
import android.os.Bundle
import io.reactivex.Single
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.redux.Action

/**
 * Created by Adam on 6/3/2017.
 */
interface MainContract {

    interface Repository {
        fun getSetListTitles() : Single<List<SetList>>
        fun setCurrentSetList(setList: String)
        fun getCurrentSetList() : SetList?
    }

    interface View {
        fun showSetList(setList : SetList?)
    }

    interface Presenter {
        fun onSetupViews(savedInstanceState: Bundle?)
        fun dispatch(action: Action)
    }
}