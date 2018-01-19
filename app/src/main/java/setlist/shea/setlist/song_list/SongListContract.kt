package setlist.shea.setlist.song_list

import com.shea.mvp.BaseContract
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import setlist.shea.domain.model.SetList
import java.io.File


/**
 * Created by Adam on 8/28/2017.
 */
interface SongListContract {

    interface Repository : BaseContract.Repository {
        fun shareSetListFile(currentSetList: SetList): Single<File>
        fun updateSetList(setList: SetList): Completable
        fun getSetList(listName: String): Flowable<SetList>
    }
}