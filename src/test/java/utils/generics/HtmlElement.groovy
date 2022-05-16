package utils.generics

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import utils.reporter.ReportLogger

class HtmlElement {
    private WebElement element
    private Select dropdown
    private Locator locator
    private ReportLogger logger
    private JavascriptExecutor js

    HtmlElement(WebDriver driver, Locator by, ReportLogger logger) {
        element = driver.findElement(by.getBy())
        locator = by
        this.logger = logger
        js = (JavascriptExecutor)driver
    }

     HtmlElement(WebElement element, Locator by, ReportLogger  logger, String xpath = null){
        this.element = element
        locator = by
         this.logger = logger
         locator.setXpath(xpath)
    }


    void click() {
        element.click()
        logger.logInfo("clicked on element: " + locator)
    }

    void sendKeys(String keys) {
        element.sendKeys(keys)
        logger.logInfo("Sent keys on element '" + locator + "' are '" + keys + "'")
    }

    void entertext(String text, boolean append = false) {
        append ? element.clear() : element
        element.sendKeys(text)
        logger.logInfo("Entered Text on element '" + locator + "' is: '" + text + "'")
    }

    HtmlElement findElement(Locator by){
        logger.logInfo("Locating element '$by' within parent '$locator'")
        return  new HtmlElement(element.findElement(by.getBy()),by, logger)
    }

    List<HtmlElement> findElements(Locator by){
        def elements = []
        def _elements = element.findElements(by.getBy())
        logger.logInfo("Locating elements '$by' within parent '$locator'")
        for(def ele : _elements){
            elements.add(new HtmlElement(ele,by, logger, by.getXPath() + "[]"))
        }
        return elements
    }

    void selectTextFromDropdown(String text) {
        dropdown = new Select(findElement(locator).element)
        dropdown.selectByVisibleText(text)
        logger.logInfo("Text selected in dropdown " + locator + " is :" + text)
    }
    void selectValueFromDropdown(String value) {
        dropdown = new Select(findElement(locator).element)
        dropdown.selectByValue(value)
        logger.logInfo("Value selected in dropdown " + locator + " is :" + value)
    }

    void selectDropdownByIndex(Locator locator, int index) {
        dropdown = new Select(findElement(locator).element)
        dropdown.selectByIndex(index)
        logger.logInfo("Index selected in dropdown " + locator + " is :" + index)
    }

    void jsClick() {
        js.executeScript("arguments[0].click()", element)
        logger.logInfo("Clicked on Element "+ locator + " using javascript")
    }

    void scrollToElement(boolean flag = true) {
        js.executeScript("arguments[0].scrollToElement($flag)", element)
        logger.logInfo("Scrolled on Element "+ locator + " using javascript")
    }

    void pressKeyOnElement(Keys key) {
        element.sendKeys(key)
        logger.logInfo("Pressed Key on element: " + locator)
    }

    String getText(){
        String text = element.getText()
        logger.logInfo("Text retrieved from element '$locator' is '$text'")
        return text
    }
}
