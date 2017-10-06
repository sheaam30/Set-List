package setlist.shea.setlist.main

import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.shea.mvp.activity.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import setlist.shea.setlist.R
import setlist.shea.setlist.main.mvp.MainContract
import javax.inject.Inject

open class MainActivity : BaseActivity<MainContract.MainPresenterInterface>(), HasSupportFragmentInjector {

    @Inject
    lateinit var mainPresenterContract: MainContract.MainPresenterInterface

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun injectDependencies() {
        AndroidInjection.inject(this)
    }

    override fun getPresenter(): MainContract.MainPresenterInterface {
        return mainPresenterContract
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.load -> { mainPresenterContract.loadSetListTitles()
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
