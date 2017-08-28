package setlist.shea.domain.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import setlist.shea.domain.model.Song

@Database(entities = arrayOf(Song::class), version = 1)
abstract class SetListDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}