package tests.uitests


import org.openqa.selenium.By
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import utils.base.UITestBase
import utils.generics.Locator

class NewTest_2 extends UITestBase{


    @Test
    void "Sauce Demo Test"()
    {
        aut.navigateToUrl("https://www.saucedemo.com/")
        aut.driver.findElement(Locator.id("user-name")).entertext("standard_user")
        aut.driver.findElement(Locator.id("password")).entertext("secret_sauce")
        aut.driver.findElement(Locator.id("login-button")).click()
        webAssert.areEquals("Verifying Title of the Page", aut.driver.getTitle(), "Swag Labs")
        def itemNameElements = aut.driver.findElements(Locator.className("inventory_item_name"))
        def names = []
        for(def name : itemNameElements){
            names.add(name.getText())
        }
        webAssert.areEquals("Verifying all Inventory Items Available on dashboard", ["Sauce Labs Backpack", "Sauce Labs Bike Light",
                                                                                     "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)"],names)
        def price = addToCart(["Sauce Labs Backpack","Sauce Labs Fleece Jacket"])
        aut.driver.findElement(Locator.className("shopping_cart_link")).jsClick()
        aut.driver.findElement(Locator.id("checkout")).jsClick()
        aut.driver.findElement(Locator.id("first-name")).entertext("CustomerFirstname")
        aut.driver.findElement(Locator.id("last-name")).entertext("CustomerLastname")
        aut.driver.findElement(Locator.id("postal-code")).entertext("123456")
        aut.driver.findElement(Locator.id("continue")).jsClick()
        String total = aut.driver.findElement(Locator.className("summary_subtotal_label")).getText().replace("Item total: \$","")
        webAssert.areEquals("Verifying if the total price displayed is correct", price.toString(),total)
    }

    def addToCart(List<String> itemNames)
    {
        def priceOfAllItems = 0.00
        for(String name : itemNames){
            aut.getElementByModifyingXpath("//div[text()='%s']/../../..//button[text()='%s']",[name,"Add to cart"]).click()
             String priceOfitem = aut.getElementByModifyingXpath("//div[text()='%s']/../../..//div[@class='inventory_item_price']",[name]).getText().trim()
            priceOfAllItems = priceOfAllItems.add(priceOfitem.replace("\$","") as BigDecimal)
        }
        return priceOfAllItems
    }
}
