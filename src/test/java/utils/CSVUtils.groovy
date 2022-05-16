package utils

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser

import java.nio.file.Files
import java.nio.file.Path

class CSVUtils {

    List<List<String>> getCSVData(String path)
    {
        Reader reader = Files.newBufferedReader(Path.of(path))
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)
        List<List<String>> rows = []
        for(def record : csvParser)
        {
            rows.add(record.toList())
        }
        return rows;
    }

}
