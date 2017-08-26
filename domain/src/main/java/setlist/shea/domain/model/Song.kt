package setlist.shea.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.opencsv.bean.CsvBindByName

@Entity
data class Song(@PrimaryKey var name : String = "",
                var artist: String = "",
                var played : Boolean = false,
                var setList : String = "")
