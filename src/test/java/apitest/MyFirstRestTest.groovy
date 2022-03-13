package apitest

import com.jayway.jsonpath.JsonPath;
import org.testng.annotations.Test
import utils.CommonUtils
import utils.apiutils.ContentType;
import utils.apiutils.RestUtils;

public class MyFirstRestTest {
    RestUtils restUtils;

    @Test
     void restTest_Get(){
        restUtils = new RestUtils();
        var response = restUtils.runGETRequest("https://www.thunderclient.com/welcome")
        // Print the status and message body of the response received from the server
        System.out.println("Status Line => " + response.getStatusLine())
        System.out.println("Response=>" + response.getBody().prettyPrint())
        System.out.println("Status=>" + response.getStatusCode())
    }

    @Test
     void restTest_Post(){
        String uri = "http://restapi.demoqa.com/customer"
        String action = "register"
        restUtils = new RestUtils();
        HashMap<String,String> body= ["FirstName":"JohnXYZ",
                                      "LastName":"XYZ",
                                      "UserName":"John.XYZ",
                                      "Password":"JohnXYZ",
                                      "Email":"JohnXYZ@opt.com"]
        var response = restUtils.runPOSTRequest(uri, action, ContentType.Json, CommonUtils.prettyPrintJson(body))
        System.out.println("Status Line => " + response.getStatusLine())
        var responseBody = JsonPath.parse(response.getBody().print())
        System.out.println("Response=>" + responseBody.read("\$.SuccessCode"))
        System.out.println("Status=>" + response.getStatusCode())

    }
//    @Test
//    public void googleMapAPITest(){
//        restUtils = new RestUtils();
//        var response = restUtils.runGETRequest("https://maps.googleapis.com")
//    }
}
