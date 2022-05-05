package utils.pages.youtube.youtubehome

import utils.ApplicationUnderTest
import utils.base.BasePage

class YoutubeHomePage extends BasePage {

    YoutubeHomePage(ApplicationUnderTest driverHelper) {
        this.aut = driverHelper
    }
    void navigate(){
        aut.navigateToUrl("https://www.youtube.com")
    }
}
