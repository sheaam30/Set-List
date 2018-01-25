package setlist.shea.setlist.songlist.di

import android.app.Application
import dagger.Module
import dagger.Provides
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SetListDao
import setlist.shea.setlist.redux.AppStore
import setlist.shea.setlist.songlist.SongListRepository
import setlist.shea.setlist.songlist.SongListViewModel

/**
 * Created by Adam on 8/28/2017.
 */
@Module
class SongListFragmentModule {

    @Provides
    fun provideListPresenter(appStore: AppStore, songListRepository: SongListRepository) : SongListViewModel {
        return SongListViewModel(appStore, songListRepository)
    }

    @Provides
    fun provideListRepository(setListDao: SetListDao, parser: Parser, writer: Writer, context : Application) : SongListRepository {
        return SongListRepository(setListDao, parser, writer, context)
    }
}