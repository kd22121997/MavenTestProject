package utils.generics


import lombok.NonNull
import org.apache.commons.io.FileUtils
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.ITestResult
import utils.Constants
import utils.reporter.ReportLogger

import java.lang.reflect.Method

class ApplicationUnderTest implements Constants {
    public HtmlWebDriver driver
    public ReportLogger logger

    ApplicationUnderTest(Method testMethod) {
        logger = new ReportLogger()
        driver = new HtmlWebDriver(logger)
        logger.startTest(testMethod)
    }

    void navigateToUrl(@NonNull String Url) {
        if (driver.webDriver == null)
            driver.initialiseDriver()
        driver.webDriver.get(Url)
        logger.logInfo("Navigated to Url: " + Url)
        waitForPageLoad()
    }

    HtmlElement getElementByModifyingXpath(String Xpath, List<String> dynamicValues) {
        String modifiedXpath = String.format(Xpath, dynamicValues.toArray())
        logger.logInfo("Xpath : '" + Xpath + "' with dynamic values : '" + dynamicValues + "' is modified to : " + modifiedXpath)
        return driver.findElement(Locator.xpath(modifiedXpath))
    }

    Object executeScript(String script, Object[] arguments = []) {
        Object results = driver.jsRunner().executeScript(script, arguments)
        logger.logInfo("Executed Javascript: " + script)
        if (arguments.size() != 0) {
            logger.logInfo("Arguments used in javascript: " + arguments)
        }
        return results
    }

    void waitForPageLoad(int timeOut) {
        driver.webDriverWait = new WebDriverWait(driver.webDriver, timeOut)
        driver.webDriverWait.until(d ->
                executeScript("return document.readyState").toString() == "complete")
        logger.logInfo("Waiting for page to load till : " + timeOut + " seconds")
    }

    void waitForPageLoad() {
        waitForPageLoad(PAGELOAD_TIMEOUT)
    }

    void openNewWindow(String url) {
        executeScript("window.open('" + url + "')")
        logger.logInfo("New Window opened with url: " + url)
    }

    void openNewWindow() {
        openNewWindow("")
    }

    void switchToWindowByIndex(int index) {
        String window = (String) driver.getWindowHandles().toArray()[index]
        driver.webDriver.switchTo().window(window)
        logger.logInfo("Window is switched to Index: " + index)
    }

    String takeSnapShot(String namePrefix) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) driver.webDriver)
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE)
        String name = namePrefix + new Date().getTime() + ".png"
        new File(SCREENSHOT_PATH).mkdirs()
        File DestFile = new File(SCREENSHOT_PATH + name)
        FileUtils.copyFile(SrcFile, DestFile)
        return SCREENSHOT_PATH + name
    }

    String takeSnapShot() throws Exception {
        return takeSnapShot(logger.className + "\\" + logger.testName)
    }

    void closeExecution(ITestResult testResult) {
        if(testResult.getStatus() == ITestResult.FAILURE)
        {
            logger.logFail(testResult.getThrowable().toString(), driver.webDriver != null ? takeSnapShot() : null)
            StringBuilder trace = new StringBuilder()
            for(def tr : testResult.throwable.getStackTrace()){
                trace.append(tr.toString() + "\n")
            }
            logger.logFailXML(trace.toString())
        }
        if (driver.webDriver != null) {
            driver.quit()
        }
    }
}
