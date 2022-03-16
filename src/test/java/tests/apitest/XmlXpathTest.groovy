package tests.apitest

import org.testng.annotations.Test
import utils.Constants
import utils.xmlutils.XmlContext

class XmlXpathTest {

    @Test
    public void testingXPath(){
        XmlContext xmlContext = new XmlContext(Constants.TESTDATAPATH + "/inventory.xml")
        var r1 = xmlContext.getNodesFromXpath("//book[@year>2001]/title")
        println("NodeList: " + r1);
        var r2 = xmlContext.getNodeFromXpath("//book[@year>2001]/title")
        println("Node :" + r2);
        var r3 = xmlContext.getAllTextFromXpath("//book[@year<2001]/title")
        println("All text from xpath :" + r3);
        var r4 = xmlContext.getValueFromXpath("//book[@year<2001]/title/text()")
        println("value :" + r4)
        var r5 = xmlContext.getAllAttributesFromXpath("//book[1]")
        println("All attributes :" + r5)
        var r6 = xmlContext.getAllAttributesFromXpath("//book[@year=1996]")
        println("All attributes :" + r6)
        xmlContext.setTextByXpath("//book[@year=1996]/title","Krushna")
        xmlContext.setAttributeByXpath("//book[@year=1996]","version","3")
        println("XML after changing text to Krushna and version to 3 :\n" + xmlContext.toXmlString())
        String r7 = xmlContext.getNodeFromXpath("//book[@year=1996]").getAttributes().getNamedItem("isAvailable").getNodeValue()
        println("get attribute value: " + r7)
        String r8 = xmlContext.getAttributeFromXpath("//book[@year=1996]","version")
        println("get attribute value using function: " + r8)

    }
}
