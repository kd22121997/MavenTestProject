package utils.pages.google.googlesearch

import org.openqa.selenium.Keys
import utils.WrapperObject
import utils.base.BasePage

class GoogleSearchPage extends BasePage implements GoogleSearchConstants {

    GoogleSearchPage(WrapperObject driverHelper) {
        this.aut = driverHelper
    }

    void navigate(){
        aut.navigateToUrl("https://www.google.com")
    }

    void search(String text){
        aut.waitElementVisible([SEARCH_INPUT, GOOGLE_SEARCH_BUTTON, I_M_FEELING_LUCKY_BUTTON])
        aut.enterText(SEARCH_INPUT,text)
        aut.pressKeyOnElement(SEARCH_INPUT, Keys.ENTER)
    }
}
