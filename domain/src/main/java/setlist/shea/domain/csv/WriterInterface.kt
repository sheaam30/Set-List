package setlist.shea.domain.csv

import setlist.shea.domain.model.Song
import java.io.File

/**
 * Created by Adam on 8/27/2017.
 */
interface WriterInterface {
    fun writeFile(file : File, songList : List<Song>)

}