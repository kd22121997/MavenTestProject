package utils.pages.google.googlesearch

import org.openqa.selenium.Keys
import utils.WebKeywords
import utils.base.BasePage

class GoogleSearchPage extends BasePage implements GoogleSearchConstants {

    GoogleSearchPage(WebKeywords driverHelper) {
        this.driverHelper = driverHelper
    }

    void navigate(){
        driverHelper.navigateToUrl("https://www.google.com")
    }

    void search(String text){
        driverHelper.waitElementVisible([SEARCH_INPUT, GOOGLE_SEARCH_BUTTON, I_M_FEELING_LUCKY_BUTTON])
        driverHelper.enterText(SEARCH_INPUT,text)
        driverHelper.pressKeyOnElement(SEARCH_INPUT, Keys.ENTER)
    }
}
