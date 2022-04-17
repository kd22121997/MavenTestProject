package utils.xmlutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class XmlContext {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public Document doc;
    public XPath xpath;

    public XmlContext(String filepath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(filepath);
        XPathFactory xpathfactory = XPathFactory.newInstance();
        xpath = xpathfactory.newXPath();
        xpath.setNamespaceContext(new NamespaceResolver(doc));
        log.info("Parsed XML from file path: '"+ filepath + "'");
    }

    public NodeList getNodesFromXpath(String xpath) throws XPathExpressionException {
        log.info("Getting Nodes from XML Xpath: '" + xpath + "'");
        XPathExpression expr = this.xpath.compile(xpath);
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        return (NodeList) result;
    }

    public Node getNodeFromXpath(String xpath) throws XPathExpressionException {
        Node node = getNodesFromXpath(xpath).item(0);
        log.info("Getting node from xpath: '" + xpath + "' : " + node);
        return  node;
    }

    public String getCommentFromXpath(String xpath) throws XPathExpressionException {
        XPathExpression expr = this.xpath.compile(xpath + "/comment()");
        Object result = expr.evaluate(doc, XPathConstants.STRING);
        return(String) result;
    }

    public HashMap<String, String> getAllAttributesFromXpath(String xpath) throws XPathExpressionException {
        var result = getNodeFromXpath(xpath).getAttributes();
        HashMap<String,String> map = new HashMap<>();
        for(int i = 0; i < result.getLength();i++){
            map.put(result.item(i).getLocalName(),result.item(i).getNodeValue());
        }
        log.info("All attributes from Xpath: '" + xpath + "' are: "+ map);
        return map;
    }

    public String getTextFromXpath(String xpath) throws XPathExpressionException {
        var resultsNode = getNodeFromXpath(xpath);
        String text = resultsNode.getTextContent();
        log.info("Text retrieved from xpath: '" + xpath + "' is : " + text);
        return text;
    }


    public List<String> getAllTextFromXpath(String xpath) throws XPathExpressionException {
        var resultNodes = getNodesFromXpath(xpath);
        List<String> texContents = new ArrayList<>();
        for (int i = 0; i < resultNodes.getLength();i++){
            texContents.add(resultNodes.item(i).getTextContent());
        }
        return  texContents;
    }

    public List<String> getAllValuesFromXpath(String xpath) throws XPathExpressionException {
        var resultNodes = getNodesFromXpath(xpath);
        List<String> values = new ArrayList<>();
        for (int i = 0; i < resultNodes.getLength();i++){
            values.add(resultNodes.item(i).getNodeValue());
        }
        return values;
    }

    public String getValueFromXpath(String xpath) throws XPathExpressionException {
        return getNodeFromXpath(xpath).getNodeValue();
    }

    public void setTextByXpath(String xpath, String text) throws XPathExpressionException {
        var node = getNodeFromXpath(xpath);
        node.setTextContent(text);
    }

    public void setValueByXpath(String xpath, String value) throws XPathExpressionException {
        var node = getNodeFromXpath(xpath);
        node.setNodeValue(value);
        log.info("Text updated in Xpath: '" + xpath + "' is: " +value);
    }

    public String getAttributeFromXpath(String xpath,String attributeName) throws XPathExpressionException {
        String value = getNodeFromXpath(xpath).getAttributes().getNamedItem(attributeName).getNodeValue();
       log.info("Value of the Attribute: '" + attributeName + "' retrieved from xpath : '" + xpath + "' is: " + value);
        return value;
    }
    public void setAttributeByXpath(String xpath,String attributeName, String attributeValue) throws XPathExpressionException {
        getNodeFromXpath(xpath).getAttributes().getNamedItem(attributeName).setNodeValue(attributeValue);
        log.info("Value of the Attribute: '" + attributeName + "'  present in xpath : '" + xpath + "' is updated to value: " + attributeValue);
    }
    public void removeAttribute(String xpath, String attributeName) throws XPathExpressionException {
        getNodeFromXpath(xpath).getAttributes().removeNamedItem(attributeName);
        log.info("Attribute: '" + attributeName + "'  present in xpath : '" + xpath + "' is removed");
    }



    public String toXmlString() throws TransformerException {
        StringWriter stringWriter = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "false");
        transformer.transform(new DOMSource(doc), new StreamResult(stringWriter));
        return stringWriter.toString();
    }
}
