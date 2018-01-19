package setlist.shea.setlist.song_list

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import setlist.shea.setlist.AppStore
import javax.inject.Inject

/**
 * Created by adamshea on 12/31/17.
 */
class SongListViewModelFactory  @Inject constructor(private val appStore: AppStore, private val songListRepository: SongListRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SongListViewModel::class.java)) {
            return SongListViewModel(appStore, songListRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}