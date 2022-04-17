package utils.base


import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import utils.Constants
import utils.WrapperObject

class UITestBase extends BasePage{

    void openChrome(){
        System.setProperty("webdriver.chrome.driver","D:\\SeleniumDrivers\\chromedriver.exe")
        aut = new WrapperObject(new ChromeDriver())
    }


    @BeforeClass
    void setup(){
        openChrome()
        aut.setImplicitWait(Constants.DRIVER_TIMEOUT)
        aut.maximizedWindow()
    }

    @AfterClass
    void cleanup(){
        aut.quit()
    }
}
