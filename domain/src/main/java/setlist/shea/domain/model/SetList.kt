package setlist.shea.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by adamshea on 9/15/17.
 */
@Entity
data class SetList(@PrimaryKey var listName: String)