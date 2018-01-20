package setlist.shea.setlist.song_list.di

import android.app.Application
import dagger.Module
import dagger.Provides
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SetListDao
import setlist.shea.setlist.Action
import setlist.shea.setlist.AppStore
import setlist.shea.setlist.song_list.SongListContract
import setlist.shea.setlist.song_list.SongListRepository
import setlist.shea.setlist.song_list.SongListViewModel
import setlist.shea.setlist.song_list.redux.PersistenceMiddleware

/**
 * Created by Adam on 8/28/2017.
 */
@Module
class SetListFragmentModule {

    @Provides
    fun provideListPresenter(appStore: AppStore, songListRepository: SongListContract.Repository) : SongListViewModel {
        return SongListViewModel(appStore, songListRepository)
    }

    @Provides
    fun provideListRepository(setListDao: SetListDao, parser: Parser, writer: Writer, context : Application) : SongListContract.Repository {
        return SongListRepository(setListDao, parser, writer, context)
    }

    @Provides
    fun providePersistenceMiddleware(appStore: AppStore, setListDao: SetListDao): PersistenceMiddleware<Action, Action> {
        return PersistenceMiddleware(appStore, setListDao)
    }
}