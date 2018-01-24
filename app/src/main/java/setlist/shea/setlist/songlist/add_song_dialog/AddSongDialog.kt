package setlist.shea.setlist.songlist.add_song_dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.Window
import android.widget.Button
import android.widget.EditText
import setlist.shea.setlist.R

/**
 * Created by Adam on 10/4/2017.
 */
class AddSongDialog(context: Context, private var addSongFunc: (name: String, artist: String, genre: String) -> Unit) : Dialog(context) {

    private lateinit var addButton : Button
    private lateinit var cancelButton : Button
    private lateinit var nameText : EditText
    private lateinit var artistText : EditText
    private lateinit var genreText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_song)
        window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        setupViews()
    }

    private fun setupViews() {
        addButton = findViewById(R.id.add)
        cancelButton = findViewById(R.id.cancel)
        nameText = findViewById(R.id.song_name)
        artistText = findViewById(R.id.song_artist)
        genreText = findViewById(R.id.song_genre)

        addButton.setOnClickListener {
            addSongFunc(nameText.text.toString(),
                    artistText.text.toString(),
                    genreText.text.toString())
            dismiss()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }
    }
}