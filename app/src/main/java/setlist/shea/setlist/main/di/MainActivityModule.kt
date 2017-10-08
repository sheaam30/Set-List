package setlist.shea.setlist.main.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import setlist.shea.domain.db.SetListDao
import setlist.shea.setlist.main.*
import setlist.shea.setlist.main.mvp.MainRepository
import setlist.shea.setlist.main.mvp.MainContract
import setlist.shea.setlist.main.mvp.MainPresenter
import setlist.shea.setlist.main.mvp.MainView

@Module
class MainActivityModule {

    @Provides
    fun provideMainPresenter(viewContract: MainContract.View, mainInteractor: MainRepository) : MainContract.Presenter {
        return MainPresenter(mainInteractor, viewContract)
    }

    @Provides
    fun provideMainInteractor(setListDao: SetListDao, sharedPreferences: SharedPreferences) : MainRepository {
        return MainRepository(setListDao, sharedPreferences)
    }

    @Provides
    fun provideMainView(activity: MainActivity) : MainContract.View {
        return MainView(activity)
    }
}
