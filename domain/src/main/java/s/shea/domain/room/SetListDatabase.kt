package s.shea.domain.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import s.shea.domain.model.Song

@Database(entities = arrayOf(Song::class), version = 1)
abstract class SetListDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao

}