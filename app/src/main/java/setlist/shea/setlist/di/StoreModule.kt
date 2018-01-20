package setlist.shea.setlist.di

import dagger.Module
import dagger.Provides
import setlist.shea.domain.db.SetListDao
import setlist.shea.setlist.AppStore
import javax.inject.Singleton

/**
 * Created by Adam on 1/20/2018.
 */
@Module
class StoreModule {

    @Provides
    @Singleton
    fun provideAppStore(setListDao: SetListDao) : AppStore {
        return AppStore(setListDao)
    }
}