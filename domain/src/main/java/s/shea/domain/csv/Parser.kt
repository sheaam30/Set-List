package s.shea.domain.csv

import com.opencsv.CSVReader
import s.shea.domain.model.Song
import java.io.InputStream
import java.io.InputStreamReader
import java.util.ArrayList

/**
 * Created by Adam on 6/4/2017.
 */
class Parser(inputStream : InputStream) {

    var csvReader : CSVReader

    init {
        csvReader = CSVReader(InputStreamReader(inputStream))
    }

    fun readFile() : List<Song> {

        val records = csvReader.readAll()
        val emps = ArrayList<Song>()

        val iterator = records.iterator()

        while (iterator.hasNext()) {
            val record = iterator.next()
            val emp = Song(record[0], record[1], record[2].toBoolean(), record[3])
            emps.add(emp)
        }
        return ArrayList(emps)
    }
}