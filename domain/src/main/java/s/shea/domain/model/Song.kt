package s.shea.domain.model

import android.arch.persistence.room.Entity
import com.opencsv.bean.CsvBindByName

@Entity
data class Song(@CsvBindByName(column = "name", required = true) var name : String,
                @CsvBindByName(column = "artist", required = true) var artist: String,
                @CsvBindByName(column = "played") var played : Boolean = false,
                @CsvBindByName(column = "setlist") var setList : String)
