package utils.generics;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;
import utils.generics.HtmlElement;
import utils.reporter.ReportLogger;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class HtmlWebDriver {

    WebDriver webDriver;
    public WebDriverWait webDriverWait;
    private final ReportLogger logger;

    HtmlWebDriver(ReportLogger logger) {
        this.logger = logger;
    }

    void initialiseDriver() throws Exception {
        var timeOuts = new HashMap<String,Long>();
        timeOuts.put("implicit",(long)Constants.ELEMENT_TIMEOUT*1000);
        timeOuts.put("pageLoad",(long)Constants.PAGELOAD_TIMEOUT*1000);
        timeOuts.put("script",(long)Constants.SCRIPT_TIMEOUT*1000);
        if(Constants.BROWSER_NAME.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", Constants.SETTINGS.getProperty("app.driverPath") + "chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setCapability("timeouts",timeOuts);
            webDriver = new ChromeDriver(options);
        }
        else if(Constants.BROWSER_NAME.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver", Constants.SETTINGS.getProperty("app.driverPath") + "chromedriver.exe");
            webDriver = new EdgeDriver();
        }
        else if(Constants.BROWSER_NAME.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", Constants.SETTINGS.getProperty("app.driverPath") + "chromedriver.exe");
            webDriver = new FirefoxDriver();
        }
        else {
            throw new Exception("The mentioned browser is not supported in this Framework: " + Constants.BROWSER_NAME);
        }
        maximizedWindow();
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

    public HtmlElement findElement(Locator by) {
        return new HtmlElement(webDriver, by, logger);
    }

    public List<HtmlElement> findElements(Locator by) {
        List<HtmlElement> elements = new ArrayList<>();
        var _elements = webDriver.findElements(by.getBy());
        int i = 1;
        for (var ele : _elements) {
            elements.add(new HtmlElement(ele, by, logger, by.getXPath()));
            i++;
        }
        return elements;
    }

    public boolean isElementPresent(By Locator) {
        boolean isPresent = webDriver.findElements(Locator).size() != 0;
        logger.logInfo("Checked if element is present : " + isPresent);
        return isPresent;
    }

    public Set<String> getWindowHandles() {
        return webDriver.getWindowHandles();
    }
}
