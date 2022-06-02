package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class CSVUtils {

    List<List<String>> getCSVData(String path) throws IOException {
        Reader reader = Files.newBufferedReader(Path.of(path));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        List<List<String>> rows = new ArrayList<>();
        for(var record : csvParser)
        {
            rows.add(record.toList());
        }
        return rows;
    }

}
