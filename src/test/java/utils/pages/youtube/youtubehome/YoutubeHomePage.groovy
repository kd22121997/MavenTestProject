package utils.pages.youtube.youtubehome

import utils.WrapperObject
import utils.base.BasePage

class YoutubeHomePage extends BasePage {

    YoutubeHomePage(WrapperObject driverHelper) {
        this.aut = driverHelper
    }
    void navigate(){
        aut.navigateToUrl("https://www.youtube.com")
    }
}
