package setlist.shea.setlist

import com.shea.mvp.interactor.BaseInteractor
import io.reactivex.Single
import io.reactivex.SingleSource
import s.shea.domain.csv.Parser
import s.shea.domain.model.Song
import java.io.InputStream
import java.io.Reader
import java.util.concurrent.Callable

/**
 * Created by Adam on 6/3/2017.
 */
class MainInteractor : BaseInteractor() {

    fun importSetList(path : String) : Single<List<Song>> {
        return Single.defer {
            var inputStream : InputStream? = SetListApp.context?.assets?.open(path)
            val parser = Parser(SetListApp.context!!.assets.open(path))
            Single.just(parser.readFile())
        }
    }
}