package setlist.shea.setlist.main.di

import dagger.Module
import dagger.Provides
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SongDao
import setlist.shea.setlist.main.MainActivity
import setlist.shea.setlist.main.MainInteractor
import setlist.shea.setlist.main.MainPresenter
import setlist.shea.setlist.main.MainView

@Module
class MainActivityModule {

    @Provides
    fun provideMainPresenter(mainView: MainView, mainInteractor: MainInteractor) : MainPresenter {
        return MainPresenter(mainInteractor, mainView)
    }

    @Provides
    fun provideMainInteractor(songDao: SongDao, parser: Parser, writer: Writer) : MainInteractor {
        return MainInteractor(songDao, parser, writer)
    }

    @Provides
    fun provideMainView(activity: MainActivity) : MainView {
        return MainView(activity)
    }
}
