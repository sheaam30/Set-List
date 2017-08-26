package setlist.shea.setlist.main

import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : com.shea.mvp.activity.BaseActivity<MainPresenter>() {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun injectDependencies() {
        super.injectDependencies()
        AndroidInjection.inject(this)
    }

    override fun getPresenter(): MainPresenter {
        return mainPresenter
    }

    override val layoutId: Int
        get() = setlist.shea.setlist.R.layout.activity_main //To change initializer of created properties use File | Settings | File Templates.
}
