package utils.pages.google.googlesearch

import org.openqa.selenium.Keys
import utils.ApplicationUnderTest
import utils.base.BasePage

class GoogleSearchPage extends BasePage implements GoogleSearchConstants {

    GoogleSearchPage(ApplicationUnderTest driverHelper) {
        this.aut = driverHelper
    }

    void navigate(){
        aut.navigateToUrl("https://www.google.com")
    }

    void search(String text){
        def searchElement = aut.driver.findElement(SEARCH_INPUT)
        searchElement.entertext(text)
        searchElement.pressKeyOnElement(Keys.ENTER)
        aut.logger.logInfo("Searched the element : "+ text)
    }
}
