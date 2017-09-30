package setlist.shea.setlist.main.di

import dagger.Module
import dagger.Provides
import setlist.shea.domain.db.SetListDao
import setlist.shea.setlist.main.*

@Module
class MainActivityModule {

    @Provides
    fun provideMainPresenter(mainViewInterface: MainInterface.MainViewInterface, mainInteractor: MainInteractor) : MainInterface.MainPresenterInterface {
        return MainPresenter(mainInteractor, mainViewInterface)
    }

    @Provides
    fun provideMainInteractor(setListDao: SetListDao) : MainInteractor {
        return MainInteractor(setListDao)
    }

    @Provides
    fun provideMainView(activity: MainActivity) : MainInterface.MainViewInterface {
        return MainView(activity)
    }
}
