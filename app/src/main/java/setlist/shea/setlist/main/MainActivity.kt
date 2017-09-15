package setlist.shea.setlist.main

import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
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

    override val layoutId: Int
        get() = setlist.shea.setlist.R.layout.activity_main

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }
}
