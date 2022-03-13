package uitest.pages.google.googlesearch;

import org.openqa.selenium.By;

public interface GoogleSearchConstants {

    By SEARCH_INPUT = By.name("q");
    By GOOGLE_SEARCH_BUTTON = By.xpath("(//input[@value='Google Search'])[2]");
    By I_M_FEELING_LUCKY_BUTTON = By.xpath("(//input[@value=\"I'm Feeling Lucky\"])[2]");
}
