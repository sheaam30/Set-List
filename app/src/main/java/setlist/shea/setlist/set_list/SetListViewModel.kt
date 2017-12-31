package setlist.shea.setlist.set_list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.tatarka.redux.android.lifecycle.StoreViewModel
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.Action
import setlist.shea.setlist.AppState
import setlist.shea.setlist.AppStore
import setlist.shea.setlist.set_list.redux.SetListActions
import javax.inject.Inject

/**
 * Created by adamshea on 12/30/17.
 */
class SetListViewModel @Inject constructor(appStore: AppStore, private val repository: SetListRepository): StoreViewModel<AppState, AppStore>(appStore) {

    fun fetchSetLists() {
        repository.setList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ setLists -> dispatch(SetListActions.SetListResponseAction(setLists)) },
                        { throwable -> })
    }

    fun addSetList(setListName: String) {
        repository.addSetList(SetList(setListName))
                .subscribeOn(Schedulers.io())
                .subscribe({ }, { })
    }

    fun dispatch(action: Action) {
        store.dispatch(action)
    }
}