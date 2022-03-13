package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    public static String getProjectPath(){
        return System.getProperty("user.dir");
    }
    public static String dateToString(Date date,String format){
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Date currentDate(){
        return new Date();
    }
}
