package setlist.shea.setlist.main.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import setlist.shea.domain.db.SetListDao
import setlist.shea.setlist.main.mvp.*
import setlist.shea.setlist.redux.AppStore

@Module
class MainActivityModule {

    @Provides
    fun provideMainPresenter(view: MainContract.View, repository: MainContract.Repository, appStore: AppStore) : MainContract.Presenter {
        return MainViewModel(repository, view, appStore)
    }

    @Provides
    fun provideMainViewModelFactory(appStore: AppStore, mainRepository: MainRepository, mainView: MainContract.View) : MainViewModelFactory =
            MainViewModelFactory(appStore, mainRepository, mainView)

    @Provides
    fun provideMainRepository(setListDao: SetListDao, sharedPreferences: SharedPreferences) : MainContract.Repository {
        return MainRepository(setListDao, sharedPreferences)
    }

    @Provides
    fun provideMainView(activity: MainActivity) : MainContract.View {
        return activity
    }
}
