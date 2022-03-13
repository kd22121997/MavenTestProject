package utils;

public interface Constants {
    String REPORT_PATH = CommonUtils.getProjectPath() + "/reports/" + CommonUtils.dateToString(CommonUtils.currentDate(), "yyyy_MM_dd_hh_mm_ss") + "/";
    String EXTENT_REPORT_NAME = "Automation Test Results.html";
    String SCREENSHOT_PATH = REPORT_PATH + "screenshots/";
    int DEFAULT_ELEMENT_TIMEOUT = 60;
    int DRIVER_TIMEOUT = 60;
    int PAGELOAD_TIMEOUT = 60;
    int CHECK_ELEMENT_TIMEOUT = 0;
}
