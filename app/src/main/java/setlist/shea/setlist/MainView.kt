package setlist.shea.setlist

import android.app.Activity
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.design.widget.BottomNavigationView
import android.view.View
import android.widget.TextView
import com.shea.mvp.activity.BaseActivity
import com.shea.mvp.presenter.BasePresenterInterface
import com.shea.mvp.view.BaseView
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by Adam on 6/3/2017.
 */
open class MainView(activity: BaseActivity<*>?) : BaseView<MainInterface>(activity) {
    lateinit var navigation: BottomNavigationView

    override fun onSetupViews(savedInstanceState: Bundle?) {
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
}