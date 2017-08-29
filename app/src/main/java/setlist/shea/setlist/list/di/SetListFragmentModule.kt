package setlist.shea.setlist.list.di

import android.content.SharedPreferences
import com.shea.mvp.activity.BaseActivity
import dagger.Module
import dagger.Provides
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SongDao
import setlist.shea.setlist.list.*

/**
 * Created by Adam on 8/28/2017.
 */
@Module
class SetListFragmentModule {

    @Provides
    fun provideListPresenter(setListViewInterface: SetListInterface.ListViewInterface, setListInteractor: SetListInteractor) : SetListInterface.ListPresenterInterface {
        return SetListPresenter(setListInteractor, setListViewInterface)
    }

    @Provides
    fun provideListInteractor(songDao: SongDao, parser: Parser, writer: Writer, sharedPreferences: SharedPreferences) : SetListInteractor {
        return SetListInteractor(songDao, parser, writer, sharedPreferences)
    }

    @Provides
    fun provideListView(fragmentSet: SetListFragment) : SetListInterface.ListViewInterface {
        return SetListView(fragmentSet.activity as BaseActivity<*>?)
    }
}