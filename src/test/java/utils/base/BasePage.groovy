package utils.base

import com.aventstack.extentreports.ExtentReports
import utils.generics.ApplicationUnderTest

class BasePage {
    protected ApplicationUnderTest aut
    protected static ExtentReports extentReports
    WebAssert webAssert
}
