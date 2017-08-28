package setlist.shea.setlist.main.di

import dagger.Module
import dagger.Provides
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SongDao
import setlist.shea.setlist.main.*

@Module
class MainActivityModule {

    @Provides
    fun provideMainPresenter(mainViewInterface: MainInterface.MainViewInterface, mainInteractor: MainInteractor) : MainInterface.MainPresenterInterface {
        return MainPresenter(mainInteractor, mainViewInterface)
    }

    @Provides
    fun provideMainInteractor(songDao: SongDao, parser: Parser, writer: Writer) : MainInteractor {
        return MainInteractor(songDao, parser, writer)
    }

    @Provides
    fun provideMainView(activity: MainActivity) : MainInterface.MainViewInterface {
        return MainView(activity)
    }
}
