package utils.base


import org.testng.Reporter
import utils.generics.ApplicationUnderTest

class WebAssert {

    ApplicationUnderTest aut

    WebAssert(ApplicationUnderTest aut)
    {
        this.aut = aut
    }

    void areEquals(String message, Object expected, Object actual)
    {
        printToReport(message, expected, actual)
    }

    void areNotEquals(String message, Object expected, Object actual)
    {
        printToReport(message, expected, actual, false)
    }

    void areTrue(String message, Boolean result)
    {
        printToReport(message, true, result)
    }

    void printToReport(String message, Object expected, Object actual, Boolean result = true)
    {
        if((expected == actual) == result)
            aut.logger.logPass("[Assertion Passed] " + message + "=> Expected: " + expected + ", Actual: " + actual)
        else
        {
            Reporter.currentTestResult.setStatus(2)
            String screenshot = aut.takeSnapShot(aut.logger.className + "\\" + aut.logger.testName.replace(".","_"))
            aut.logger.logFail("[Assertion Failed] " + message + "=> Expected: " + expected + ", Actual: " + actual, screenshot)
        }
    }
}
