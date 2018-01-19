package setlist.shea.setlist.set_list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.tatarka.redux.android.lifecycle.StoreViewModel
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.Action
import setlist.shea.setlist.AppState
import setlist.shea.setlist.AppStore
import setlist.shea.setlist.set_list.redux.SetListActions
import setlist.shea.setlist.set_list.redux.SetListState
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

    fun addSetList(setListName: String) {
        disposable.add(repository.addSetList(SetList(setListName))
                .subscribeOn(Schedulers.io())
                .subscribe({ }, { }))
    }

    fun deleteSetList(setList: SetList) {
        disposable.add(repository.deleteSetList(setList)
                .subscribeOn(Schedulers.io())
                .subscribe({ }, { }))
    }

    override fun onCleared() {
        disposable.clear()
    }

    fun dispatch(action: Action) {
        store.dispatch(action)
    }
}