package utils.base

import com.aventstack.extentreports.ExtentReports
import utils.ApplicationUnderTest

class BasePage {
    public ApplicationUnderTest aut
    public static ExtentReports extentReports
    WebAssert webAssert
}
