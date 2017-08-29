package setlist.shea.setlist;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import setlist.shea.domain.db.SongDao;
import setlist.shea.domain.model.Song;

/**
 * Created by Adam on 6/4/2017.
 */

public class Sample {

    public SongDao songDao;

    public List<Song> songs = new ArrayList<>();

    @Inject
    public Sample(SongDao songDao) {
        super();

    }
}
