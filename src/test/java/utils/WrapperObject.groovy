package utils;

import lombok.NonNull;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait
import org.slf4j.Logger
import org.slf4j.LoggerFactory;

import java.time.Duration;

class WrapperObject implements Constants {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    WebDriver driver;
    WebDriverWait webDriverWait;

     WrapperObject(WebDriver driver) {
        this.driver = driver;
    }

     WebDriver getDriver() {
        return driver;
    }

     void maximizedWindow() {
        driver.manage().window().maximize();
        log.info("Window is maximised");
    }

     String getTitle() {
        String title = driver.getTitle();
        log.info("The title of the Page is " + title);
        return title;
    }

     void navigateToUrl(@NonNull String Url) {
        driver.get(Url);
        log.info("Navigated to Url: " + Url);
        waitForPageLoad();
    }

     void setImplicitWait(int timeInSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ZERO.withSeconds(timeInSeconds));
        log.info("Driver's implicit wait is set to " + timeInSeconds + " seconds");
    }

     WebElement getElement(@NonNull By locator)
    {
        log.info("Locating element: " + locator);
        return driver.findElement(locator);
    }

     List<WebElement> getElements(@NonNull By locator) {
        log.info("Locating all occurance of element: " + locator);
        return driver.findElements(locator);
    }

     WebElement getElementByModifyingXpath(String Xpath, List<String> dynamicValues) {
        String modifiedXpath = String.format(Xpath, dynamicValues);
        log.info("Xpath : '" +Xpath + "' with dynamic values : '" + dynamicValues + "' is modified to : " + modifiedXpath);
        return getElement(By.xpath(modifiedXpath));
    }

     void selectTextFromDropdown(By locator, String text) {
        Select dropdown = new Select(getElement(locator));
        dropdown.selectByVisibleText(text);
        log.info("Text selected in dropdown " + locator + " is :" + text)
    }

     void selectValueFromDropdown(By locator, String value) {
        Select dropdown = new Select(getElement(locator));
        dropdown.selectByValue(value);
         log.info("Value selected in dropdown " + locator + " is :" + value)
     }

     void selectDropdownByIndex(By locator, int index) {
        Select dropdown = new Select(getElement(locator));
        dropdown.selectByIndex(index);
         log.info("Index selected in dropdown " + locator + " is :" + index)
     }

     void enterText(By locator, String text) {
        getElement(locator).clear();
        getElement(locator).sendKeys(text);
         log.info("Text Enter in the element " + locator + " is :" + text)
     }

     JavascriptExecutor jsRunner() {
        return ((JavascriptExecutor) driver);
    }

    Object executeScript(String script, Object[] arguments = [])
    {
        Object results = jsRunner().executeScript(script,arguments)
        log.info("Executed Javascript: " + script)
        log.info("Arguments used in javascript: " + arguments)
        return  results;
    }

     void jsClick(By locator) {
        executeScript("arguments[0].click();", getElement(locator));
         log.info("Clicked on Element "+ locator + " using javascript")
    }

     void scrollToElement(By locator) {
        executeScript("arguments[0].scrollToElement(true)", getElement(locator));
         log.info("Scrolled on Element "+ locator + " using javascript")
     }

     void waitForPageLoad(int timeOut) {
        webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(d ->
                ((JavascriptExecutor) d).executeScript("return document.readyState;").toString().equals("complete"));
         log.info("Waiting for page to load till : " + timeOut + " seconds")
    }

     void waitForPageLoad() {
        waitForPageLoad(PAGELOAD_TIMEOUT);
    }

     void waitElementVisible(List<Object> elements, int timeOut) {
        webDriverWait = new WebDriverWait(driver, timeOut);
        for (def locator : elements) {
            if (locator instanceof By) {
                webDriverWait.until(ExpectedConditions.visibilityOf(getElement((By) locator)));
            } else if (locator instanceof WebElement) {
                webDriverWait.until(ExpectedConditions.visibilityOf((WebElement) locator));
            }
            log.info("Waiting for visibility of element: " + locator + " for " + timeOut + " seconds")
        }
    }

     void waitElementVisible(List<Object> locators) {
        waitElementVisible(locators, DEFAULT_ELEMENT_TIMEOUT);
    }

     void waitElementVisible(By locator) {
        var locators = new ArrayList<>();
        locators.add(locator);
        waitElementVisible(locators);
    }

     void pressKeyOnElement(By locator, Keys key) {
        pressKeyOnElement(getElement(locator), key);
         log.info("Pressed Key on element: " + locator)
     }

     void pressKeyOnElement(WebElement element, Keys key) {
        element.sendKeys(key);
         log.info("Pressed Key: " + key.name())
    }

     void openNewWindow(String url) {
        executeScript("window.open('" + url + "')");
         log.info("New Window opened with url: " + url)
    }

     void openNewWindow() {
        openNewWindow("");
    }

     void switchToWindowByIndex(int index) {
        String window = (String) driver.getWindowHandles().toArray()[index];
        driver.switchTo().window(window);
        log.info("Window is switched to Index: " + index)
    }

     String takeSnapShot(String namePrefix) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        String name = namePrefix + new Date().getTime() + ".png";
        var _mkdir = new File(SCREENSHOT_PATH).mkdirs();
        File DestFile = new File(SCREENSHOT_PATH + name);
        FileUtils.copyFile(SrcFile, DestFile);
        return SCREENSHOT_PATH + name;
    }

     String takeSnapShot() throws Exception {
        return takeSnapShot("");
    }

     boolean isElementPresent(By Locator) {
        int timeOut = (int) driver.manage().timeouts().getImplicitWaitTimeout().getSeconds();
        setImplicitWait(CHECK_ELEMENT_TIMEOUT);
        boolean ispresent = getElements(Locator).size() != 0;
        setImplicitWait(timeOut);
         log.info("Checked if element is present : " + ispresent)
        return ispresent;
    }

     Capabilities getCapabilities() {
        return ((RemoteWebDriver) driver).getCapabilities();
    }

     String getBrowserName() {
        return getCapabilities().getBrowserName();
    }

     void quit() {
        driver.quit();
         log.info("Driver window is closed, no further operations can take place on this driver")
    }

     void closeWindow() {
        driver.close();
         log.info("window is closed")
     }

}
