package setlist.shea.setlist.song_list.di

import android.app.Application
import dagger.Module
import dagger.Provides
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.db.SongDao
import setlist.shea.setlist.song_list.mvp.SongListContract
import setlist.shea.setlist.song_list.mvp.SongListFragment
import setlist.shea.setlist.song_list.mvp.SongListPresenter
import setlist.shea.setlist.song_list.mvp.SongListRepository

/**
 * Created by Adam on 8/28/2017.
 */
@Module
class SetListFragmentModule {

    @Provides
    fun provideListPresenter(songViewContract: SongListContract.View, songListRepository: SongListContract.Repository) : SongListContract.Presenter {
        return SongListPresenter(songListRepository, songViewContract)
    }

    @Provides
    fun provideListRepository(songDao: SongDao, setListDao: SetListDao, parser: Parser, writer: Writer, context : Application) : SongListContract.Repository {
        return SongListRepository(songDao, setListDao, parser, writer, context)
    }

    @Provides
    fun provideListView(fragmentSet: SongListFragment) : SongListContract.View {
        return fragmentSet
    }
}