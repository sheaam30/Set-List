package setlist.shea.setlist.list.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.db.SongDao
import setlist.shea.setlist.list.mvp.SetListFragment
import setlist.shea.setlist.list.mvp.SetListContract
import setlist.shea.setlist.list.mvp.SetListRepository
import setlist.shea.setlist.list.mvp.SetPresenter

/**
 * Created by Adam on 8/28/2017.
 */
@Module
class SetListFragmentModule {

    @Provides
    fun provideListPresenter(setViewContract: SetListContract.View, setListInteractor: SetListRepository) : SetListContract.Presenter {
        return SetPresenter(setListInteractor, setViewContract)
    }

    @Provides
    fun provideListInteractor(songDao: SongDao, setListDao: SetListDao, parser: Parser, writer: Writer, sharedPreferences: SharedPreferences) : SetListRepository {
        return SetListRepository(songDao, setListDao, parser, writer, sharedPreferences)
    }

    @Provides
    fun provideListView(fragmentSet: SetListFragment) : SetListContract.View {
        return fragmentSet
    }
}