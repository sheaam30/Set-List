package setlist.shea.domain.di

import dagger.Module
import dagger.Provides
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.ParserInterface
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.csv.WriterInterface

/**
 * Created by Adam on 7/5/2017.
 */
@Module
class CsvModule {

    @Provides
    fun getCsvParser() : ParserInterface {
        return Parser()
    }

    @Provides
    fun provideCsvWriter() : WriterInterface {
        return Writer()
    }
}
