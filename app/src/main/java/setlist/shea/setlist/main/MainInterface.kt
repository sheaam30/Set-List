package setlist.shea.setlist.main

import com.shea.mvp.presenter.BaseInterface

/**
 * Created by Adam on 6/3/2017.
 */
interface MainInterface {

    interface MainViewInterface : BaseInterface.BaseViewInterface {
        fun showPage(index : Int)
    }

    interface MainPresenterInterface : BaseInterface.BasePresenterInterface {
        fun pageClicked(index : Int)
    }
}