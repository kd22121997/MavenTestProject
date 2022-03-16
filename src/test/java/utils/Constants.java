package utils;

import java.util.Properties;

public interface Constants {
    String REPORT_PATH = CommonUtils.getProjectPath() + "/reports/" + CommonUtils.dateToString(CommonUtils.currentDate(), "yyyy_MM_dd_HH_mm_ss") + "/";
    String EXTENT_REPORT_NAME = "Automation Test Results.html";
    String SCREENSHOT_PATH = REPORT_PATH + "screenshots/";
    int DEFAULT_ELEMENT_TIMEOUT = 60;
    int DRIVER_TIMEOUT = 60;
    int PAGELOAD_TIMEOUT = 60;
    int CHECK_ELEMENT_TIMEOUT = 0;
    String SETTINGS_PROPERTIES_PATH = CommonUtils.getProjectPath() + "/src/test/resources/config/settings.properties";
    String JSONFOLDERPATH =  CommonUtils.getProjectPath() + "/src/test/resources/jsonfiles";
    String TESTDATAPATH = CommonUtils.getProjectPath() + "/src/test/resources/testdata";
}
