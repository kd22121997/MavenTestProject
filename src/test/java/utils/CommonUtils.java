package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static Properties readProperties(String path) {
        FileReader reader = null;
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties p = new Properties();
        try {
            p.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

    public static Properties getSettings() {
        return readProperties(Constants.SETTINGS_PROPERTIES_PATH);
    }
}
