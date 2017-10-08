package s.shea.domain

import android.arch.persistence.room.Room
import org.junit.After
import android.support.test.InstrumentationRegistry
import org.junit.Before
import setlist.shea.domain.db.SetListDatabase


/**
 * Created by Adam on 10/5/2017.
 */
abstract class DbTest {
    protected var db: SetListDatabase? = null

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                SetListDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        db?.close()
    }
}