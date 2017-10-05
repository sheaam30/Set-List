package setlist.shea.setlist.list.add_song_dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import setlist.shea.setlist.R

/**
 * Created by Adam on 10/4/2017.
 */
class AddSongDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_song)
    }
}