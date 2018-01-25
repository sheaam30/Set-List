package setlist.shea.setlist.main.mvp

import android.arch.lifecycle.ViewModelStore
import android.content.DialogInterface
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.tatarka.redux.android.lifecycle.StoreViewModel
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.redux.Action
import setlist.shea.setlist.redux.AppState
import setlist.shea.setlist.redux.AppStore
import javax.inject.Inject


/**
 * Created by Adam on 6/3/2017.
 */
class MainViewModel @Inject constructor(private var mainRepository: MainContract.Repository,
                                        private var mainView: MainContract.View,
                                        appStore: AppStore)
    : StoreViewModel<AppState, AppStore>(appStore), MainContract.Presenter {

    override fun onSetupViews(savedInstanceState: Bundle?) {
        val setList = mainRepository.getCurrentSetList()
        mainView.showSetList(setList)
    }

    override fun dispatch(action: Action) {
        store.dispatch(action)
    }
}