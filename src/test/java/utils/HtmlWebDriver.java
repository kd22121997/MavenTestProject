package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.reporter.ReportLogger;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HtmlWebDriver {

    WebDriver webDriver;
    public WebDriverWait webDriverWait;
    private final ReportLogger logger;

    HtmlWebDriver(ReportLogger logger) {
        this.logger = logger;
    }

    void initialiseDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\SeleniumDrivers\\chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    public void maximizedWindow() {
        webDriver.manage().window().maximize();
        logger.logInfo("Window is maximised");
    }

    public String getTitle() {
        String title = webDriver.getTitle();
        logger.logInfo("The title of the Page is " + title);
        return title;
    }

    public void setImplicitWait(int timeInSeconds) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ZERO.withSeconds(timeInSeconds));
        logger.logInfo("Driver's implicit wait is set to " + timeInSeconds + " seconds");
    }

    public JavascriptExecutor jsRunner() {
        return ((JavascriptExecutor) webDriver);
    }

    public Capabilities getCapabilities() {
        return ((RemoteWebDriver) webDriver).getCapabilities();
    }

    public String getBrowserName() {
        return getCapabilities().getBrowserName();
    }

    public void quit() {
        webDriver.quit();
        logger.logInfo("Driver window is closed, no further operations can take place on this driver");
    }

    public void closeWindow() {
        webDriver.close();
        logger.logInfo("window is closed");
    }

    public HtmlElement findElement(By by) {
        return new HtmlElement(webDriver, by, logger);
    }

    public List<HtmlElement> findElements(By by){
        List<HtmlElement> elements = new ArrayList<>();
        var _elements = webDriver.findElements(by);
        for(var ele : _elements){
            elements.add(new HtmlElement(ele,by,logger));
        }
        return elements;
    }

    public boolean isElementPresent(By Locator) {
        boolean isPresent = webDriver.findElements(Locator).size() != 0;
        logger.logInfo("Checked if element is present : " + isPresent);
        return isPresent;
    }

    public Set<String> getWindowHandles(){
        return webDriver.getWindowHandles();
    }
}
