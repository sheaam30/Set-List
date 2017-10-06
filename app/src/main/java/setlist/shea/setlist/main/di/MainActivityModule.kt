package setlist.shea.setlist.main.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import setlist.shea.domain.db.SetListDao
import setlist.shea.setlist.main.*
import setlist.shea.setlist.main.mvp.MainInteractor
import setlist.shea.setlist.main.mvp.MainContract
import setlist.shea.setlist.main.mvp.MainPresenter
import setlist.shea.setlist.main.mvp.MainView

@Module
class MainActivityModule {

    @Provides
    fun provideMainPresenter(mainViewContract: MainContract.MainViewInterface, mainInteractor: MainInteractor) : MainContract.MainPresenterInterface {
        return MainPresenter(mainInteractor, mainViewContract)
    }

    @Provides
    fun provideMainInteractor(setListDao: SetListDao, sharedPreferences: SharedPreferences) : MainInteractor {
        return MainInteractor(setListDao, sharedPreferences)
    }

    @Provides
    fun provideMainView(activity: MainActivity) : MainContract.MainViewInterface {
        return MainView(activity)
    }
}
