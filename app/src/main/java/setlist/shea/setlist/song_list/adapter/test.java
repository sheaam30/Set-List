package setlist.shea.setlist.song_list.adapter;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Adam on 1/19/2018.
 */

public class test {
    SongViewHolder songViewHolder;

    public test() {
        songViewHolder.getMoveItem().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }
}
