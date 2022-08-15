package utils.generics

import org.openqa.selenium.By

class Locator {
    String xpath
    By by

    private Locator(){ }

    Locator(By by, String xpath){
        this.by = by
        this.xpath = xpath
    }

    static Locator id(String id) {
        return new Locator(xpath: "//*[@id=\"$id\"]", by: By.id(id))
    }

    static Locator name(String name) {
        return new Locator(xpath: "//*[@name=\"name\"]", by: By.name(name))
    }

    static Locator xpath(String xpath) {
        return new Locator(xpath: xpath, by: By.xpath(xpath))
    }

    static Locator cssSelector(String cssSelector) {
        return new Locator(xpath: "//*[@css=\"$cssSelector\"]", by: By.cssSelector(cssSelector))
    }

    static Locator linkText(String linkText) {
        return new Locator(xpath: "//*[text()=\"$linkText\"]", by: By.linkText(linkText))
    }

    static Locator className(String className) {
        return new Locator(xpath: "//*[@class=\"$className\"]", by: By.className(className))
    }

    By getBy(){
        return by
    }

    String toString(){
        return xpath;
    }
}
