package utils.base


import org.openqa.selenium.chrome.ChromeDriver
import org.testng.ITestResult
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeClass
import utils.Constants
import utils.WebKeywords

class UITestBase extends BasePage{

    void openChrome(){
        System.setProperty("webdriver.chrome.driver","D:\\SeleniumDrivers\\chromedriver.exe")
        driverHelper = new WebKeywords(new ChromeDriver())
    }

    @BeforeClass
    void setup(){
        openChrome()
        driverHelper.setImplicitWait(Constants.DRIVER_TIMEOUT)
        driverHelper.maximizedWindow()
    }
    @AfterMethod
    void checkIfFailed(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            String screenshotpath = driverHelper.takeSnapShot(result.getMethod().getMethodName())
            result.setAttribute("fail_screenshot",screenshotpath)
            System.out.println(result.getAttribute("fail_screenshot"))
        }

    }

    @AfterClass
    void cleanup(){
        driverHelper.quit()
    }
}
