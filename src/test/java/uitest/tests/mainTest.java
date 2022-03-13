package uitest.tests;

import org.openqa.selenium.chrome.ChromeDriver;

public class mainTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","D:\\SeleniumDrivers\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.quit();
    }
}
