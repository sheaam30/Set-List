package setlist.shea.setlist;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import setlist.shea.domain.db.SetListDao;
import setlist.shea.domain.db.SongDao;
import setlist.shea.domain.model.Song;

/**
 * Created by Adam on 6/4/2017.
 */

public class Sample {

    public SetListDao setListDao;

    public List<Song> songs = new ArrayList<>();

    @Inject
    public Sample(SongDao songDao) {
        super();
        String[] strings = new String[1];
        strings[0] = "";
    }
}
