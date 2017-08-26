package setlist.shea.setlist.main

import com.shea.mvp.presenter.BasePresenterInterface

/**
 * Created by Adam on 6/3/2017.
 */
interface MainInterface : BasePresenterInterface {

    fun pageClicked(index : Int)
}