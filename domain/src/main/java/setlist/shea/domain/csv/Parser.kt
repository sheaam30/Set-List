package setlist.shea.domain.csv

import com.opencsv.CSVReader
import setlist.shea.domain.model.Song
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

/**
 * Created by Adam on 6/4/2017.
 */
class Parser : ParserInterface {

    lateinit var csvReader : CSVReader

    override fun readFile(inputStream: InputStream?) : List<Song> {

        csvReader = CSVReader(InputStreamReader(inputStream))

        val records = csvReader.readAll()
        val songs = ArrayList<Song>()

        val iterator = records.iterator()

        while (iterator.hasNext()) {
            val record = iterator.next()
            val emp = Song(record[0], record[1], record[2].toBoolean(), record[3])
            songs.add(emp)
        }
        return ArrayList(songs)
    }
}