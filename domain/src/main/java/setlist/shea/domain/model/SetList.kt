package setlist.shea.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by adamshea on 9/15/17.
 */

@Entity
data class SetList(@PrimaryKey var listName: String = "", var songs: List<Song> = emptyList()) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createTypedArrayList(Song)) {
    }

    override fun equals(other: Any?): Boolean {
        if (other !is SetList) return false
        if (other.listName != listName) return false
        if (other.songs == songs) return true
        return false
    }

    override fun hashCode(): Int {
        var result = listName.hashCode()
        result = 31 * result + songs.hashCode()
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(listName)
        parcel.writeTypedList(songs)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SetList> {
        override fun createFromParcel(parcel: Parcel): SetList {
            return SetList(parcel)
        }

        override fun newArray(size: Int): Array<SetList?> {
            return arrayOfNulls(size)
        }
    }
}