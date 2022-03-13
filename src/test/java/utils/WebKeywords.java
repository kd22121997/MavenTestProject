package utils;

import lombok.NonNull;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebKeywords implements Constants{
    WebDriver driver;
    WebDriverWait webDriverWait;

    public WebKeywords(WebDriver driver){
        this.driver = driver;
    }
    public WebDriver getDriver(){
        return driver;
    }

    public void maximizedWindow(){
        driver.manage().window().maximize();
    }

    public String getTitle(){
       return driver.getTitle();
    }

    public void navigateToUrl(@NonNull String Url){
        driver.get(Url);
        waitForPageLoad();
    }

    public void setImplicitWait(int timeInSeconds){
        driver.manage().timeouts().implicitlyWait(Duration.ZERO.withSeconds(timeInSeconds));
    }

    public WebElement getElement(@NonNull By locator){
        return driver.findElement(locator);
    }
    public List<WebElement> getElements(@NonNull By locator){
        return driver.findElements(locator);
    }

    public WebElement getElementByModifyingXpath(String Xpath, List<String> dynamicValues){
        return driver.findElement(By.xpath(String.format(Xpath,dynamicValues)));
    }

    public void selectTextFromDropdown(By locator, String text){
        Select dropdown = new Select(getElement(locator));
        dropdown.selectByVisibleText(text);
    }

    public void selectValueFromDropdown(By locator, String value){
        Select dropdown = new Select(getElement(locator));
        dropdown.selectByValue(value);
    }

    public void selectDropdownByIndex(By locator, int index){
        Select dropdown = new Select(getElement(locator));
        dropdown.selectByIndex(index);
    }

    public void enterText(By locator,String text){
        getElement(locator).clear();
        getElement(locator).sendKeys(text);
    }

    public JavascriptExecutor jsRunner() { return ((JavascriptExecutor)driver); }

    public void jsClick(By locator){
        jsRunner().executeScript("arguments[0].click();",getElement(locator));
    }

    public void scrollToElement(By locator){
        jsRunner().executeScript("arguments[0].scrollToElement(true)",getElement(locator));
    }

    public void waitForPageLoad(int timeOut){
        webDriverWait = new WebDriverWait(driver,timeOut);
        webDriverWait.until(d ->
                ((JavascriptExecutor)d).executeScript("return document.readyState;").toString().equals("complete"));
    }

    public void waitForPageLoad(){
        waitForPageLoad(PAGELOAD_TIMEOUT);
    }

    public void waitElementVisible(List<Object> elements,int timeOut){
        webDriverWait = new WebDriverWait(driver,timeOut);
        for (var locator:elements){
            if(locator instanceof By){
                webDriverWait.until(ExpectedConditions.visibilityOf(getElement((By)locator)));
            }
            else if(locator instanceof WebElement){
                webDriverWait.until(ExpectedConditions.visibilityOf((WebElement)locator));
            }
        }
    }
    public void waitElementVisible(List<Object> locators){
        waitElementVisible(locators,DEFAULT_ELEMENT_TIMEOUT);
    }

    public void waitElementVisible(By locator){
        var locators = new ArrayList<>();
        locators.add(locator);
        waitElementVisible(locators);
    }

    public void pressKeyOnElement(By locator, Keys key){
       pressKeyOnElement(getElement(locator),key);
    }

    public void pressKeyOnElement(WebElement element, Keys key){
        element.sendKeys(key);
    }

    public void openNewWindow(String url){
        jsRunner().executeScript("window.open('"+url+"')");
    }

    public void openNewWindow(){
        openNewWindow("");
    }

    public void switchToWindowByIndex(int index){
        String window = (String) driver.getWindowHandles().toArray()[index];
        driver.switchTo().window(window);
    }

    public String takeSnapShot(String namePrefix) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        String name = namePrefix + new Date().getTime() + ".png";
        var _mkdir  = new File(SCREENSHOT_PATH).mkdirs();
        File DestFile=new File(SCREENSHOT_PATH + name);
        FileUtils.copyFile(SrcFile, DestFile);
        return SCREENSHOT_PATH + name;
    }

    public String takeSnapShot() throws Exception {
        return takeSnapShot("");
    }

    public boolean isElementPresent(By Locator){
        int timeOut = (int)driver.manage().timeouts().getImplicitWaitTimeout().getSeconds();
        setImplicitWait(CHECK_ELEMENT_TIMEOUT);
        boolean ispresent = getElements(Locator).size() !=0;
        setImplicitWait(timeOut);
        return ispresent;
    }

    public Capabilities getCapabilities(){
        return ((RemoteWebDriver) driver).getCapabilities();
    }

    public String getBrowserName(){
        return getCapabilities().getBrowserName();
    }

    public void quit(){
        driver.quit();
    }
    public void closeWindow(){
        driver.close();
    }

}
