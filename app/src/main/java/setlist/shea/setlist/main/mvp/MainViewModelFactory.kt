package setlist.shea.setlist.main.mvp

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import setlist.shea.setlist.redux.AppStore
import setlist.shea.setlist.setlist.SetListRepository
import setlist.shea.setlist.setlist.SetListViewModel
import javax.inject.Inject

/**
 * Created by Adam on 1/23/2018.
 */

/**
 * Created by adamshea on 12/30/17.
 */
class MainViewModelFactory @Inject constructor(private val appStore: AppStore, private val mainRepository: MainRepository, private val mainView: MainContract.View) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mainRepository, mainView, appStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}