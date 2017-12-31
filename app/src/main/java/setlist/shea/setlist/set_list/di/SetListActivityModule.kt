package setlist.shea.setlist.set_list.di

import dagger.Module
import dagger.Provides
import setlist.shea.domain.db.SetListDao
import setlist.shea.setlist.AppStore
import setlist.shea.setlist.set_list.SetListRepository
import setlist.shea.setlist.set_list.SetListViewModelFactory

/**
 * Created by adamshea on 12/30/17.
 */
@Module
class SetListActivityModule {

    @Provides
    fun provideSetListRepository(setListDao: SetListDao): SetListRepository =
        SetListRepository(setListDao)

    @Provides
    fun provideSetListViewModelFactory(appStore: AppStore, setListRepository: SetListRepository) : SetListViewModelFactory =
            SetListViewModelFactory(appStore, setListRepository)
}