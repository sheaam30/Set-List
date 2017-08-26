package setlist.shea.domain.di

import dagger.Module
import dagger.Provides
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer

/**
 * Created by Adam on 7/5/2017.
 */
@Module
class CsvModule {

    @Provides
    fun getCsvParser() : Parser {
        return Parser()
    }

    @Provides
    fun provideCsvWriter() : Writer {
        return Writer()
    }
}
