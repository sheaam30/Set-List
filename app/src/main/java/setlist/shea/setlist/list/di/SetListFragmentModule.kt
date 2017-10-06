package setlist.shea.setlist.list.di

import android.content.SharedPreferences
import com.shea.mvp.activity.BaseActivity
import dagger.Module
import dagger.Provides
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.db.SongDao
import setlist.shea.setlist.list.SetListFragment
import setlist.shea.setlist.list.mvp.SetListContract
import setlist.shea.setlist.list.mvp.SetListInteractor
import setlist.shea.setlist.list.mvp.SetListPresenter
import setlist.shea.setlist.list.mvp.SetListView

/**
 * Created by Adam on 8/28/2017.
 */
@Module
class SetListFragmentModule {

    @Provides
    fun provideListPresenter(setListViewContract: SetListContract.ListViewInterface, setListInteractor: SetListInteractor) : SetListContract.ListPresenterInterface {
        return SetListPresenter(setListInteractor, setListViewContract)
    }

    @Provides
    fun provideListInteractor(songDao: SongDao, setListDao: SetListDao, parser: Parser, writer: Writer, sharedPreferences: SharedPreferences) : SetListInteractor {
        return SetListInteractor(songDao, setListDao, parser, writer, sharedPreferences)
    }

    @Provides
    fun provideListView(fragmentSet: SetListFragment) : SetListContract.ListViewInterface {
        return SetListView(fragmentSet.activity as BaseActivity<*>?)
    }
}