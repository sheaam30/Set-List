package setlist.shea.setlist.list

import com.shea.mvp.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Adam on 8/28/2017.
 */
class SetListPresenter constructor(setListInteractor: SetListInteractor, view: SetListInterface.ListViewInterface) : BasePresenter<SetListInteractor, SetListInterface.ListViewInterface>(setListInteractor, view), SetListInterface.ListPresenterInterface {

    var disposables : CompositeDisposable = CompositeDisposable()

    init {
        val currentSetList = setListInteractor.getCurrentSetList()
        if (currentSetList.isNullOrEmpty()) {
            view.showEmptyState()
        } else {
            view.showListState()
            disposables.add(setListInteractor.getSetList(currentSetList!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ songs ->
                        view.displaySongs(songs)
                    }, {
                        t: Throwable -> view.showErrorState()
                    }))
        }
    }

    override fun onPause() {
        super.onPause()
        disposables.clear()
    }
}