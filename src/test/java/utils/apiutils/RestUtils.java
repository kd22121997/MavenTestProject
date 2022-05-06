package utils.apiutils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.generics.ApplicationUnderTest;

public class RestUtils {

    ApplicationUnderTest aut;

    public RestUtils(ApplicationUnderTest aut) {
        this.aut = aut;
    }

    public Response runGETRequest(String URI, String action) {
        RestAssured.baseURI = URI;
        RequestSpecification httpRequest = RestAssured.given();
        aut.logger.logInfo("Running Get Request on URI : '" + URI + "'");
        return httpRequest.request(Method.GET, modifyAction(action));
    }

    public Response runGETRequest(String URI) {
        return runGETRequest(URI, "");
    }


    public Response runPOSTRequest(String URI, String action, ContentType contentType, String requestBodyJson) {
        RestAssured.baseURI = URI;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", contentType.getValue());
        httpRequest.body(requestBodyJson);
        return httpRequest.request(Method.POST, "/" + modifyAction(action));
    }

    public Response runPOSTRequest(String URI, ContentType contentType, String requestBodyJson) {
        RestAssured.baseURI = URI;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", contentType.getValue());
        httpRequest.body(requestBodyJson);
        aut.logger.logInfo("Running Post request on URI '" + URI + "' and Request Body: '" + requestBodyJson + "'");
        return httpRequest.request(Method.POST);
    }

    String modifyAction(String action) {
        return action.equals("") || action == null ? "" : action.startsWith("/") ? action : "/" + action;
    }

}
