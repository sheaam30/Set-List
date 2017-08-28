package setlist.shea.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Song(@PrimaryKey var name : String = "",
                var artist: String = "",
                var played : Boolean = false,
                var genre : String = "",
                var setList : String = "")