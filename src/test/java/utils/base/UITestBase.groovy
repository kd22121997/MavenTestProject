package utils.base

import org.testng.ITestResult
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeSuite
import utils.generics.ApplicationUnderTest
import utils.reporter.ExtentManager
import utils.reporter.ReportLogger

import java.lang.reflect.Method

class UITestBase extends BasePage {

    @BeforeSuite
    void initialize() {
        extentReports = ExtentManager.createInstance()
        ReportLogger.setExtentReport(extentReports)
    }

    @BeforeMethod
    void startTest(Method testMethod) {
        aut = new ApplicationUnderTest(testMethod)
        webAssert = new WebAssert(aut)
    }

    @AfterMethod
    void closeDriver(ITestResult testResult) {
        aut.closeExecution(testResult)
    }

    @AfterSuite
    void procescleanup() {
        if (extentReports != null) {
            extentReports.flush()
        }
    }

}
