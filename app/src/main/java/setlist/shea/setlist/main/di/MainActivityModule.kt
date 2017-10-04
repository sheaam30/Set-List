package setlist.shea.setlist.main.di

import dagger.Module
import dagger.Provides
import setlist.shea.domain.db.SetListDao
import setlist.shea.setlist.main.*
import setlist.shea.setlist.main.mvp.MainInteractor
import setlist.shea.setlist.main.mvp.MainInterface
import setlist.shea.setlist.main.mvp.MainPresenter
import setlist.shea.setlist.main.mvp.MainView

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
