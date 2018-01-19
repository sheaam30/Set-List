package setlist.shea.domain.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song

@Database(entities = arrayOf(Song::class, SetList::class), version = 1)
@TypeConverters(CustomConverter::class)
abstract class SetListDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun setListDao() : SetListDao
}