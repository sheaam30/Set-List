package setlist.shea.domain.csv

import setlist.shea.domain.model.Song
import java.io.InputStream

/**
 * Created by Adam on 8/27/2017.
 */
interface ParserInterface {
    fun readFile(inputStream: InputStream?) : List<Song>

}