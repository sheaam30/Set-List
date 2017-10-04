package setlist.shea.setlist.main

import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import setlist.shea.setlist.R
import setlist.shea.setlist.main.mvp.MainInterface
import javax.inject.Inject

open class MainActivity : com.shea.mvp.activity.BaseActivity<MainInterface.MainPresenterInterface>(), HasSupportFragmentInjector {

    @Inject
    lateinit var mainPresenterInterface: MainInterface.MainPresenterInterface

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun injectDependencies() {
        AndroidInjection.inject(this)
    }

    override fun getPresenter(): MainInterface.MainPresenterInterface {
        return mainPresenterInterface
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.load -> { mainPresenterInterface.loadSetListTitles()
                return true
            }
        }
        return false
    }

    override val layoutId: Int
        get() = setlist.shea.setlist.R.layout.activity_main

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }
}
