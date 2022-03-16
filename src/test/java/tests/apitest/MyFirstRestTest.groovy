package tests.apitest

import com.jayway.jsonpath.JsonPath
import org.testng.annotations.Test
import utils.CommonUtils
import utils.Constants
import utils.apiutils.ContentType
import utils.apiutils.RestUtils

public class MyFirstRestTest {
    RestUtils restUtils;

    @Test
    void restTest_Get() {
        restUtils = new RestUtils();
        var response = restUtils.runGETRequest("https://www.thunderclient.com/welcome")
        // Print the status and message body of the response received from the server
        System.out.println("Status Line => " + response.getStatusLine())
        System.out.println("Response=>" + response.getBody().prettyPrint())
        System.out.println("Status=>" + response.getStatusCode())
    }

    @Test
    void restTest_Post() {
        String uri = "http://ip.jsontest.com/"
        def bodyContext = JsonPath.parse(new File(Constants.JSONFOLDERPATH +"/postrequest.json"))
        String bodyJson = CommonUtils.prettyPrintJson(bodyContext.json())
        restUtils = new RestUtils();
        var response = restUtils.runPOSTRequest(uri, ContentType.Json,bodyJson)
        System.out.println("Status Line => " + response.getStatusLine())
        var responseBody = JsonPath.parse(response.getBody().print())
        System.out.println("IP address received from Response=>" + responseBody.read("\$.ip"))
        System.out.println("Status=>" + response.getStatusCode())

    }
}
