package setlist.shea.setlist

import android.os.Bundle
import com.shea.mvp.presenter.BasePresenter

/**
 * Created by Adam on 6/3/2017.
 */
class MainPresenter internal constructor(interactor: MainInteractor, view: MainView) : BasePresenter<MainInteractor, MainView>(interactor, view), MainInterface {

    override fun onSetupViews(savedInstanceState: Bundle?) {
        super.onSetupViews(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStart() {
        super.onStart()
        view.attach(this)
    }

    override fun onStop() {
        super.onStop()
        view.detach()
    }

    override fun onSaveState(outState: Bundle) {
        super.onSaveState(outState)
    }
}