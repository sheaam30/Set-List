package setlist.shea.setlist.main

import com.shea.mvp.activity.BaseActivity
import com.shea.mvp.view.BaseView
import setlist.shea.setlist.R
import setlist.shea.setlist.list.SetListFragment

/**
 * Created by Adam on 6/3/2017.
 */
open class MainView(activity: BaseActivity<*>?) : BaseView<MainInterface.MainPresenterInterface>(activity), MainInterface.MainViewInterface {

    override fun onSetupViews(savedInstanceState: android.os.Bundle?) {
    }

    override fun showList() {
        val setListFragment = SetListFragment.newInstance()
        getFragmentManager().beginTransaction()
                .replace(R.id.content, setListFragment)
                .commit()
    }
}