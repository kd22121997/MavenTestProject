package utils.pages.youtube.youtubehome

import utils.WebKeywords
import utils.base.BasePage

class YoutubeHomePage extends BasePage {

    YoutubeHomePage(WebKeywords driverHelper) {
        this.driverHelper = driverHelper
    }
    void navigate(){
        driverHelper.navigateToUrl("https://www.youtube.com")
    }
}
