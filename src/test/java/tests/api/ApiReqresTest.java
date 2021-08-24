package tests.api;

import baseEntities.BaseReqresAPITest;
import endpoints.ReqresEndpoints;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.ReqresUser;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiReqresTest extends BaseReqresAPITest {
    int endpointTWO = 2;
    int endpointTHREE = 3;
    int endpointTwentyThree = 23;

    @Test
    public void listUsersTest() {
        //Setup request
        RequestSpecification httpRequest = given();

        //Setup response
        Response listUsersResponse = httpRequest.request(Method.GET, String.format(ReqresEndpoints.ALl_USERS, endpointTWO));
        System.out.println(listUsersResponse.asString());
        System.out.println(listUsersResponse.getHeaders().toString());

        //Get response body
        String responseBody = listUsersResponse.getBody().asString();
        System.out.println(responseBody);

        //Get response status
        int statusCode = listUsersResponse.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);
    }


    @Test
    public void listUsersTest_2() {
        given()
                .when()
                .get(String.format(ReqresEndpoints.ALl_USERS, endpointTWO))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();

    }

    @Test
    public void singleUserTest() {
        RequestSpecification httpRequest = given();
        Response singleUserResponse = httpRequest.request(Method.GET, String.format(ReqresEndpoints.ONE_USER, endpointTWO));
        System.out.println(singleUserResponse.asString());
        System.out.println(singleUserResponse.getHeaders().toString());
        String responseBody = singleUserResponse.getBody().asString();
        System.out.println(responseBody);
        int statusCode = singleUserResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public void singleUserTest_2() {
        given()
                .when()
                .get( String.format(ReqresEndpoints.ONE_USER, endpointTWO))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();

    }

    @Test
    public void singleUserNotFoundTest() {
        RequestSpecification httpRequest = given();
        Response singleUserNotFoundResponse = httpRequest.request(Method.GET, String.format(ReqresEndpoints.ONE_USER, endpointTwentyThree));

        System.out.println(singleUserNotFoundResponse.asString());
        System.out.println(singleUserNotFoundResponse.getHeaders().toString());

        String responseBody = singleUserNotFoundResponse.getBody().asString();
        System.out.println(responseBody);

        int statusCode = singleUserNotFoundResponse.getStatusCode();
        Assert.assertEquals(statusCode, 404);

    }

    @Test
    public void singleUserNotFoundTest_2() {
        given()
                .when()
                .get(String.format(ReqresEndpoints.ONE_USER, endpointTwentyThree))
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().body();

    }

    @Test
    public void listResourcesTest() {
        RequestSpecification httpRequest = given();
        Response listResourcesResponse = httpRequest.request(Method.GET, ReqresEndpoints.RESOURCES);
        System.out.println(listResourcesResponse.asString());
        System.out.println(listResourcesResponse.getHeaders().toString());

        String responseBody = listResourcesResponse.getBody().asString();
        System.out.println(responseBody);

        int statusCode = listResourcesResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public void listResourcesTest_2() {
        given()
                .when()
                .get(ReqresEndpoints.RESOURCES)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }

    @Test
    public void singleResourcesTest() {
        RequestSpecification httpRequest = given();
        Response singleResourcesResponse = httpRequest.request(Method.GET, String.format(ReqresEndpoints.SINGLE_RESOURCES, endpointTWO));
        System.out.println(singleResourcesResponse.asString());
        System.out.println(singleResourcesResponse.getHeaders().toString());

        String responseBody = singleResourcesResponse.getBody().asString();
        System.out.println(responseBody);

        int statusCode = singleResourcesResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void singleResourcesTest_2() {
        given()
                .when()
                .get(String.format(ReqresEndpoints.SINGLE_RESOURCES, endpointTWO))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }

    @Test
    public void singleResourcesNotFoundTest() {
        RequestSpecification httpRequest = given();
        Response singleResourcesNotFoundResponse = httpRequest.request(Method.GET, String.format(ReqresEndpoints.SINGLE_RESOURCES, endpointTwentyThree));
        System.out.println(singleResourcesNotFoundResponse.asString());
        System.out.println(singleResourcesNotFoundResponse.getHeaders().toString());
        String responseBody = singleResourcesNotFoundResponse.getBody().asString();
        System.out.println(responseBody);
        int statusCode = singleResourcesNotFoundResponse.getStatusCode();
        Assert.assertEquals(statusCode, 404);
    }

    @Test
    public void singleResourcesNotFoundTest_2() {
        given()
                .when()
                .get(String.format(ReqresEndpoints.SINGLE_RESOURCES, endpointTwentyThree))
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().body();
    }

    @Test
    public void deleteTest() {
        RequestSpecification httpRequest = given();
        Response deleteResponse = httpRequest.request(Method.DELETE, String.format(ReqresEndpoints.ONE_USER, endpointTWO));
        System.out.println(deleteResponse.asString());

        String responseBody = deleteResponse.getBody().asString();
        System.out.println(responseBody);

        int statusCode = deleteResponse.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }

    @Test
    public void deleteTest_2() {
        given()
                .when()
                .delete(String.format(ReqresEndpoints.ONE_USER, endpointTWO))
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .log().body();
    }

    @Test
    public void delayedTest() {
        RequestSpecification httpRequest = given();
        Response delayedResponse = httpRequest.request(Method.GET, String.format(ReqresEndpoints.DELAYED_USER, endpointTHREE));
        System.out.println(delayedResponse.asString());

        String responseBody = delayedResponse.getBody().asString();
        System.out.println(responseBody);
        int statusCode = delayedResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public void delayedTest_2() {
        given()
                .when()
                .get(String.format(ReqresEndpoints.DELAYED_USER, endpointTHREE))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }

    @Test
    public void createTest() {
        RequestSpecification httpRequest = given();
        ReqresUser user =ReqresUser.builder()
                .name("morpheus")
                .job("leader")
                .build();
        httpRequest.body(String.format("{\n" +
                "  \"name\": \"%s\",\n" +
                "  \"job\": \"%s\"\n" +
                "}", user.getName(), user.getJob()));
        Response postResponse = httpRequest.request(Method.POST, ReqresEndpoints.USERS);
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    @Test
    public void createTest_2() {
        ReqresUser user = ReqresUser.builder()
                .name("morpheus")
                .job("leader")
                .build();

        Response response = given()
                .body(String.format("{\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"job\": \"%s\"\n" +
                        "}", user.getName(),user.getJob()))
                .when()
                .post(ReqresEndpoints.USERS)
                .then()
                .log().body()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED);
    }

    @Test
    public void updateTest() {
        ReqresUser user = ReqresUser.builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        RequestSpecification httpRequest = given();
        httpRequest.body(String.format("{\n" +
                "  \"name\": \"%s\",\n" +
                "  \"job\": \"%s\"\n" +
                "}", user.getName(), user.getJob()));
        Response postResponse = httpRequest.request(Method.PUT, String.format(ReqresEndpoints.ONE_USER, endpointTWO));
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void updateTest_2() {
        ReqresUser user = ReqresUser.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        Response response = given()
                .body(String.format("{\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"job\": \"%s\"\n" +
                        "}", user.getName(), user.getJob()))
                .when()
                .put(String.format(ReqresEndpoints.ONE_USER, endpointTWO))
                .then()
                .log().body()
                .extract().response();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void patchTest() {
        ReqresUser user =ReqresUser.builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        RequestSpecification httpRequest = given();
        httpRequest.body(String.format("{\n" +
                "  \"name\": \"%s\",\n" +
                "  \"job\": \"%s\"\n" +
                "}", user.getName(), user.getJob()));
        Response postResponse = httpRequest.request(Method.PATCH, String.format(ReqresEndpoints.ONE_USER, endpointTWO));
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void patchTest_2() {
        ReqresUser user = ReqresUser.builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        given()
                .body(String.format("{\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"job\": \"%s\"\n" +
                        "}", user.getName(), user.getJob()))
                .when()
                .patch(String.format(ReqresEndpoints.ONE_USER, endpointTWO))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void registerSuccessfulTest() {
        ReqresUser user = ReqresUser.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        RequestSpecification httpRequest = given();
        httpRequest.body(String.format("{\n" +
                "    \"email\": \"%s\",\n" +
                "    \"password\": \"%s\"\n" +
                "}", user.getEmail(), user.getPassword()));
        Response postResponse = httpRequest.request(Method.POST, ReqresEndpoints.REGISTER_USER);
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void registerSuccessfulTest_2() {
        ReqresUser user = ReqresUser.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        Response response = given()
                .body(String.format("{\n" +
                "    \"email\": \"%s\",\n" +
                "    \"password\": \"%s\"\n" +
                "}", user.getEmail(), user.getPassword()))
                .when()
                .post(ReqresEndpoints.REGISTER_USER)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void registerUnsuccessfulTest() {
        ReqresUser user = ReqresUser.builder()
                .email("sydney@fife")
                .build();

        RequestSpecification httpRequest = given();
        httpRequest.body(String.format("{\n" +
                "    \"email\": \"%s\"\n" +
                "}", user.getEmail()));
        Response postResponse = httpRequest.request(Method.POST,ReqresEndpoints.REGISTER_USER);
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 400);
    }

    @Test
    public void registerUnsuccessfulTest_2() {
        ReqresUser user = ReqresUser.builder()
                .email("sydney@fife")
                .build();
        Response response = given()
        .body(String.format("{\n" +
                "    \"email\": \"%s\"\n" +
                "}", user.getEmail()))
                .when()
                .post(ReqresEndpoints.REGISTER_USER)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 400);
    }

    @Test
    public void loginSuccessfulTest() {
        ReqresUser user = ReqresUser.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();

        RequestSpecification httpRequest = given();
        httpRequest.body(String.format("{\n" +
                "    \"email\": \"%s\",\n" +
                "    \"password\": \"%s\"\n" +
                "}", user.getEmail(), user.getPassword()));
        Response postResponse = httpRequest.request(Method.POST, ReqresEndpoints.LOGIN_USER);
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void loginSuccessfulTest_2() {
        ReqresUser user = ReqresUser.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        Response response = given()
                .body(String.format("{\n" +
                        "    \"email\": \"%s\",\n" +
                        "    \"password\": \"%s\"\n" +
                        "}", user.getEmail(), user.getPassword()))
                .when()
                .post(ReqresEndpoints.LOGIN_USER)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void loginUnsuccessfulTest() {
        ReqresUser user = ReqresUser.builder()
                .email("peter@klaven")
                .build();

        RequestSpecification httpRequest = given();
        httpRequest.body(String.format("{\n" +
                "    \"email\": \"%s\"\n" +
                "}", user.getEmail()));
        Response postResponse = httpRequest.request(Method.POST, ReqresEndpoints.LOGIN_USER);
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 400);
    }

    @Test
    public void loginUnsuccessfulTest_2() {
        ReqresUser user = ReqresUser.builder()
                .email("peter@klaven")
                .build();
        Response response = given()
                .body(String.format("{\n" +
                        "    \"email\": \"%s\"\n" +
                        "}", user.getEmail()))
                .when()
                .post(ReqresEndpoints.LOGIN_USER)
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), 400);
    }
}

