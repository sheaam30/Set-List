package setlist.shea.setlist.list.add_song_dialog

/**
 * Created by Adam on 10/5/2017.
 */
abstract class AddSongCallback {
    abstract fun addSongClicked(songName : String, songArtist : String, songGenre : String)
}