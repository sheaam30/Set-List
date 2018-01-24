package setlist.shea.setlist.setlist

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.tatarka.redux.android.lifecycle.StoreViewModel
import setlist.shea.setlist.redux.Action
import setlist.shea.setlist.redux.AppState
import setlist.shea.setlist.redux.AppStore
import setlist.shea.setlist.setlist.redux.SetListActions
import setlist.shea.setlist.setlist.redux.SetListState
import javax.inject.Inject

/**
 * Created by adamshea on 12/30/17.
 */
class SetListViewModel @Inject constructor(appStore: AppStore,
                                           private val repository: SetListRepository):
        StoreViewModel<AppState, AppStore>(appStore) {

    private val disposable = CompositeDisposable()

    //Clear the state every time a new Set List State is instantiated
    init {
        appStore.state.setListState = SetListState()
    }

    fun fetchSetLists() {
        disposable.add(repository.setList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ setLists -> dispatch(SetListActions.Updated(setLists)) },
                        { }))
    }

    override fun onCleared() {
        disposable.clear()
    }

    fun dispatch(action: Action) {
        store.dispatch(action)
    }
}