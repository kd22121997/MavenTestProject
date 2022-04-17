package tests.uitests

import org.testng.Assert
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import utils.base.UITestBase
import utils.pages.google.googlesearch.GoogleSearchPage
import utils.pages.youtube.youtubehome.YoutubeHomePage

class TestNgGroovyUITest extends UITestBase {

    GoogleSearchPage googleSearchPage
    YoutubeHomePage youtubeHomePage

    @BeforeClass
    void start() {
        googleSearchPage = new GoogleSearchPage(aut)
        youtubeHomePage = new YoutubeHomePage(aut)
    }

    @Test(priority = 0)
    void NavigateGoogle() {
        googleSearchPage.navigate()
        Assert.assertEquals(aut.getTitle(), "Google", "Checking Title")
    }

    @Test(priority = 1)
    void SearchSomething() {
        googleSearchPage.search("Iron Man")
        aut.takeSnapShot()
    }

    @Test(priority = 2)
    void OpenYoutubeInANewTab() {
        aut.openNewWindow()
        aut.switchToWindowByIndex(1)
        youtubeHomePage.navigate()
        println(aut.getDriver().getTitle())
    }

    @Test
    void GroovyBenefits()
    {
        //Create List
        def list = ["A","B","R"]
        //Create HashMap
        def hashmap = [("Name"):("Albert"),("Gender"):("Male"),("Age"):("24")]
        //compare list
        boolean verified = list == ["B","V","V"]
        //compare hashmap
        verified = hashmap == [("Name"):("Sonia"),("Gender"):("Female"),("Age"):("24")]
        //Creating nested list
        def nestedlist = [[["list1"],["list1"]],[["list2"],["list2"]]]
    }

}
