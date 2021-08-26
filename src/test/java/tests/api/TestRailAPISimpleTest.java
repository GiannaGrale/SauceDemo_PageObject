package tests.api;

import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.protocol.HTTP;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestRailAPISimpleTest {

    @Test
    public void getAllUsers (){

        RestAssured.baseURI = ReadProperties.getInstance().getTestRailSite();
        String endpoint ="index.php?/api/v2/get_users";
        RequestSpecification httpRequest = given();
        httpRequest.header(HTTP.CONTENT_TYPE, ContentType.JSON);
        httpRequest.auth().preemptive().basic
                (ReadProperties.getInstance().getLogin(),
                ReadProperties.getInstance().getPassword());

        //Setup response
        Response listUsersResponse = httpRequest.request(Method.GET, endpoint);
        System.out.println(listUsersResponse.asString());

        //Get response body
        String responseBody = listUsersResponse.getBody().asString();
        System.out.println(responseBody);

        //Get response status
        int statusCode = listUsersResponse.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode,200);
    }
}
