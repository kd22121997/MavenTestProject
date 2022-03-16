package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtils {

    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    public static String dateToString(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Date currentDate() {
        return new Date();
    }

    public static String prettyPrintJson(Object json) throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    public static Properties readProperties(String path) throws IOException {
        FileReader reader = new FileReader(path);
        Properties p = new Properties();
        p.load(reader);
        return p;
    }

    public static Properties getSettings() throws IOException {
        return readProperties(Constants.SETTINGS_PROPERTIES_PATH);
    }
}
