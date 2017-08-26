package setlist.shea.setlist;


import javax.inject.Inject;

import setlist.shea.domain.db.SongDao;

/**
 * Created by Adam on 6/4/2017.
 */

public class Sample {

    public SongDao songDao;

    @Inject
    public Sample(SongDao songDao) {
        super();

    }
}
