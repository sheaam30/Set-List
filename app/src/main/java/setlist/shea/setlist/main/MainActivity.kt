package setlist.shea.setlist.main

import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : com.shea.mvp.activity.BaseActivity<MainInterface.MainPresenterInterface>() {

    @Inject
    lateinit var mainPresenterInterface: MainInterface.MainPresenterInterface

    override fun injectDependencies() {
        super.injectDependencies()
        AndroidInjection.inject(this)
    }

    override fun getPresenter(): MainInterface.MainPresenterInterface {
        return mainPresenterInterface
    }

    override val layoutId: Int
        get() = setlist.shea.setlist.R.layout.activity_main
}
