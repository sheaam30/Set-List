package setlist.shea.setlist.main

import android.support.design.widget.BottomNavigationView
import com.shea.mvp.activity.BaseActivity
import com.shea.mvp.view.BaseView
import setlist.shea.setlist.R

/**
 * Created by Adam on 6/3/2017.
 */
open class MainView(activity: BaseActivity<*>?) : BaseView<MainInterface.MainPresenterInterface>(activity), MainInterface.MainViewInterface {
    lateinit var navigation: BottomNavigationView

    override fun onSetupViews(savedInstanceState: android.os.Bundle?) {
        navigation = bind(R.id.navigation)

        navigation.setOnNavigationItemSelectedListener  { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    true
                }
                R.id.navigation_dashboard -> {
                    true
                }
                R.id.navigation_notifications -> {
                    true
                }
            }
            false
        }
    }

    override fun showPage(index: Int) {
        presenterInterface?.pageClicked(index)
    }
}