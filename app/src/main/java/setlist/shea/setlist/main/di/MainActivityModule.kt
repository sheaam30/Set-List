package setlist.shea.setlist.main.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import setlist.shea.domain.db.SetListDao
import setlist.shea.setlist.main.mvp.MainActivity
import setlist.shea.setlist.main.mvp.MainContract
import setlist.shea.setlist.main.mvp.MainPresenter
import setlist.shea.setlist.main.mvp.MainRepository

@Module
class MainActivityModule {

    @Provides
    fun provideMainPresenter(view: MainContract.View, repository: MainRepository) : MainContract.Presenter {
        return MainPresenter(repository, view)
    }

    @Provides
    fun provideMainRepository(setListDao: SetListDao, sharedPreferences: SharedPreferences) : MainRepository {
        return MainRepository(setListDao, sharedPreferences)
    }

    @Provides
    fun provideMainView(activity: MainActivity) : MainContract.View {
        return activity
    }

}
