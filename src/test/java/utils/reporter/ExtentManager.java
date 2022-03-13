package utils.reporter;

import utils.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager implements Constants {

    private static ExtentReports extent;

    public static ExtentReports createInstance(){
        boolean _mkdirs = new File(REPORT_PATH).mkdirs();
        String path = REPORT_PATH + EXTENT_REPORT_NAME;
        var sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setDocumentTitle("Automation Reports");
        sparkReporter.config().setReportName("Automation Test Results");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.setSystemInfo("Organization","Krushna Lmt");
        extent.setSystemInfo("Browser","Chrome");
        extent.attachReporter(sparkReporter);
        return  extent;
    }

}
