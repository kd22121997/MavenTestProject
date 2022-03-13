package uitest.pages.youtube.youtubehome

import utils.base.BasePage
import utils.WebKeywords

class YoutubeHomePage extends BasePage {

    YoutubeHomePage(WebKeywords driverHelper) {
        this.driverHelper = driverHelper
    }
    void navigate(){
        driverHelper.navigateToUrl("https://www.youtube.com")
    }
}
