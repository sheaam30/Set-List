package setlist.shea.domain.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.db.SetListDatabase
import setlist.shea.domain.db.SongDao
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideSongDao(setListDatabase: SetListDatabase): SongDao {
        return setListDatabase.songDao()
    }

    @Provides
    @Singleton
    fun provideSetListDao(setListDatabase: SetListDatabase): SetListDao {
        return setListDatabase.setListDao()
    }

    @Provides
    @Singleton
    fun provideSetListDatabase(context: Context): SetListDatabase {
        return Room.databaseBuilder(context, SetListDatabase::class.java, "SetList").build()
    }
}