package utils.generics

import org.openqa.selenium.By

class Locator {

    String type
    String value
    private String xpath
    private By by

    private Locator(){ }

    String getXPath() {
        if (xpath == null) {
            if (type == "xpath")
                xpath = "($value)[1]"
            else if (type == "linkText") {
                xpath = "(//a[text()=\"$value\"])"
            } else
                xpath = "(//*[@$type=\"$value\"])"
        }
        return xpath
    }

    protected void setXpath(String xpath = null)
    {
        this.xpath = xpath
    }

    static Locator id(String id) {
        return new Locator(type: "id", value: id, by: By.id(id))
    }

    static Locator name(String name) {
        return new Locator(type: "name", value: name, by: By.name(name))
    }

    static Locator xpath(String xpath) {
        return new Locator(type: "xpath", value: xpath, by: By.xpath(xpath))
    }

    static Locator cssSelector(String cssSelector) {
        return new Locator(type: "css", value: cssSelector, by: By.id(cssSelector))
    }

    static Locator linkText(String linkText) {
        return new Locator(type: "linkText", value: linkText, by: By.linkText(linkText))
    }

    static Locator className(String className) {
        return new Locator(type: "className", value: className, by: By.className(className))
    }

    By getBy(){
        return by
    }

    String toString(){
        return getXPath()
    }
}
