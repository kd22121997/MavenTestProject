package utils.apiutils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtils {

    public Response runGETRequest(String URI){
        RestAssured.baseURI = URI;
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest.request(Method.GET,"/Hyderabad");
    }

}
