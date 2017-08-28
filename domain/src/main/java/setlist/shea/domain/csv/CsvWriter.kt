package setlist.shea.domain.csv

import com.opencsv.CSVWriter
import setlist.shea.domain.model.Song
import java.io.File
import java.io.FileWriter
import java.util.*


/**
 * Created by Adam on 6/4/2017.
 */
class CsvWriter : Writer {

    override fun writeFile(file : File, songList : List<Song>) {
        val writer = FileWriter(file)
        //using custom delimiter and quote character
        val csvWriter = CSVWriter(writer, ',')
        val data = toStringArray(songList)
        csvWriter.writeAll(data)
        csvWriter.close()
    }

    private fun toStringArray(songList: List<Song>) : ArrayList<Array<String>> {
        val returnList = ArrayList<Array<String>>()

        for (song : Song in songList) {
            returnList.add(arrayOf(song.name, song.artist, song.played.toString(), song.setList))
        }
        return returnList
    }
}