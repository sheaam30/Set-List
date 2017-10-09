package setlist.shea.setlist.list.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.db.SongDao
import setlist.shea.setlist.list.mvp.SetListContract
import setlist.shea.setlist.list.mvp.SetListFragment
import setlist.shea.setlist.list.mvp.SetListPresenter
import setlist.shea.setlist.list.mvp.SetListRepository

/**
 * Created by Adam on 8/28/2017.
 */
@Module
class SetListFragmentModule {

    @Provides
    fun provideListPresenter(setViewContract: SetListContract.View, setListRepository: SetListContract.Repository) : SetListContract.Presenter {
        return SetListPresenter(setListRepository, setViewContract)
    }

    @Provides
    fun provideListRepository(songDao: SongDao, setListDao: SetListDao, parser: Parser, writer: Writer, sharedPreferences: SharedPreferences) : SetListContract.Repository {
        return SetListRepository(songDao, setListDao, parser, writer, sharedPreferences)
    }

    @Provides
    fun provideListView(fragmentSet: SetListFragment) : SetListContract.View {
        return fragmentSet
    }
}