package s.shea.domain.csv;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import s.shea.domain.model.Song;

/**
 * Created by Adam on 6/4/2017.
 */

public class TestParser {

    public TestParser(InputStream inputStream) {
        CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
    }

    public List<Song> getSongList() {
        return new ArrayList<>();
    }
}
