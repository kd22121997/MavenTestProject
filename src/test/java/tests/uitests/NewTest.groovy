package tests.uitests


import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import utils.base.UITestBase
import utils.pages.google.googlesearch.GoogleSearchPage

class NewTest extends UITestBase{

    GoogleSearchPage googleSearchPage

    @BeforeMethod
    void openWindow()
    {
        googleSearchPage = new GoogleSearchPage(aut)
        googleSearchPage.navigate()
    }

    @Test
    void "Verify Title"()
    {
        webAssert.areEquals("Checking Title","Google",aut.driver.getTitle())
        googleSearchPage.search("Selenium")
    }
}
