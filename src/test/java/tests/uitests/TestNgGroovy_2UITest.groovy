package tests.uitests

import org.testng.Assert
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import utils.base.UITestBase
import utils.pages.google.googlesearch.GoogleSearchPage
import utils.pages.youtube.youtubehome.YoutubeHomePage

class TestNgGroovy_2UITest extends UITestBase {

    GoogleSearchPage googleSearchPage
    YoutubeHomePage youtubeHomePage

    @BeforeClass
    void start() {
        googleSearchPage = new GoogleSearchPage(driverHelper)
        youtubeHomePage = new YoutubeHomePage(driverHelper)
    }

    @Test(priority = 0)
    void NavigateGoogle() {
        googleSearchPage.navigate()
        Assert.assertEquals(driverHelper.getDriver().getTitle(), "Google", "Checking Title")
    }

    @Test(priority = 1)
    void SearchSomething() {
        googleSearchPage.search("Iron Man")
        driverHelper.takeSnapShot()
    }

    @Test(priority = 2)
    void OpenYoutubeInANewTab() {
        driverHelper.openNewWindow()
        driverHelper.switchToWindowByIndex(1)
        youtubeHomePage.navigate()
        println(driverHelper.getDriver().getTitle())
    }

}
