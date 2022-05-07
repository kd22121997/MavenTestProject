package utils.pages.google.googlesearch;

import org.openqa.selenium.By;
import utils.generics.Locator;

public interface GoogleSearchConstants {

    Locator SEARCH_INPUT = Locator.name("q");
    Locator GOOGLE_SEARCH_BUTTON = Locator.xpath("(//input[@value='Google Search'])[2]");
    Locator I_M_FEELING_LUCKY_BUTTON = Locator.xpath("(//input[@value=\"I'm Feeling Lucky\"])[2]");
}
