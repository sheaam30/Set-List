package s.shea.domain

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import setlist.shea.domain.model.SetList

/**
 * Created by Adam on 10/5/2017.
 */
@RunWith(AndroidJUnit4::class)
class SetListDaoTest : DbTest() {

    @Test
    fun insertSetList() {
        db?.setListDao()?.insertSetList(SetList("The Ritz", emptyList()))
    }
}