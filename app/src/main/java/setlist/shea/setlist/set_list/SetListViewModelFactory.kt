package setlist.shea.setlist.set_list

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import setlist.shea.setlist.AppStore
import javax.inject.Inject


/**
 * Created by adamshea on 12/30/17.
 */
class SetListViewModelFactory @Inject constructor(private val appStore: AppStore, private val setListRepository: SetListRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetListViewModel::class.java)) {
            return SetListViewModel(appStore, setListRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}