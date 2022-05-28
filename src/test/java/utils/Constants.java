package utils;

import java.util.Properties;

public interface Constants {
    String SETTINGS_PROPERTIES_PATH = CommonUtils.getProjectPath() + "/src/test/resources/config/settings.properties";
    Properties SETTINGS = CommonUtils.getSettings();
    String REPORT_PATH = CommonUtils.getProjectPath() + SETTINGS.getProperty("app.report.path") + "/" + CommonUtils.dateToString(CommonUtils.currentDate(), "yyyy_MM_dd_HH_mm_ss") + "/";
    String EXTENT_REPORT_NAME = SETTINGS.getProperty("app.report.extentReportName");
    String SCREENSHOT_PATH = REPORT_PATH + SETTINGS.getProperty("app.report.screenshotFolder");
    String BROWSER_NAME = SETTINGS.getProperty("ui.browser");
    int ELEMENT_TIMEOUT = Integer.parseInt(SETTINGS.getProperty("ui.timeout.element"));
    int DRIVER_TIMEOUT = Integer.parseInt(SETTINGS.getProperty("ui.timeout.driver"));
    int PAGELOAD_TIMEOUT = Integer.parseInt(SETTINGS.getProperty("ui.timeout.pageLoad"));
    int SCRIPT_TIMEOUT = Integer.parseInt(SETTINGS.getProperty("ui.timeout.script"));
    String JSONFOLDERPATH = CommonUtils.getProjectPath() + SETTINGS.getProperty("app.jsonFolderPath");
    String TESTDATAPATH = CommonUtils.getProjectPath() + SETTINGS.getProperty("app.testDataFolderPath");

}
