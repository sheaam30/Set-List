package setlist.shea.setlist

import android.os.Bundle
import com.shea.mvp.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

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
        interactor.importSetList("sample_sheet.csv")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { Timber.i("Success") }, Consumer { throwable -> Timber.e(throwable) })
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