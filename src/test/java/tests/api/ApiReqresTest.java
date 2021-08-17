package tests.api;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiReqresTest {

    @Test
    public void listUsersTest() {

        String endpoint = "/api/users?page=2";

        //RestAssured settings
        RestAssured.baseURI = "https://reqres.in/";

        //Setup request
        RequestSpecification httpRequest = given();

        //Setup response
        Response listUsersResponse = httpRequest.request(Method.GET, endpoint);
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
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();

    }

    @Test
    public void singleUserTest() {
        String endpoint = "api/users/2";
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = given();
        Response singleUserResponse = httpRequest.request(Method.GET, endpoint);
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
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();

    }

    @Test
    public void singleUserNotFoundTest() {
        String endpoint = "/api/users/23";
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = given();
        Response singleUserNotFoundResponse = httpRequest.request(Method.GET, endpoint);

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
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().body();

    }

    @Test
    public void listResourcesTest() {
        String endpoint = "/api/unknown";
        RestAssured.baseURI = "https://reqres.in";

        RequestSpecification httpRequest = given();
        Response listResourcesResponse = httpRequest.request(Method.GET, endpoint);
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
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }

    @Test
    public void singleResourcesTest() {
        String endpoint = "/api/unknown/2";
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification httpRequest = given();
        Response singleResourcesResponse = httpRequest.request(Method.GET, endpoint);
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
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }

    @Test
    public void singleResourcesNotFoundTest() {
        String endpoint = "/api/unknown/23";
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification httpRequest = given();
        Response singleResourcesNotFoundResponse = httpRequest.request(Method.GET, endpoint);
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
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().body();
    }

    @Test
    public void deleteTest() {
        String endpoint = "/api/users/2";
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification httpRequest = given();
        Response deleteResponse = httpRequest.request(Method.DELETE, endpoint);
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
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .log().body();
    }

    @Test
    public void delayedTest() {
        String endpoint = "/api/users?delay=3";
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification httpRequest = given();
        Response delayedResponse = httpRequest.request(Method.GET, endpoint);
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
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }


    //Post, patch, put. Не уверена, что верно
    @Test
    public void createTest() {
        String endpoint = "/api/users";
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification httpRequest = given();
        httpRequest.body("{\n" +
                "  \"name\": \"morpheus\",\n" +
                "  \"job\": \"leader\"\n" +
                "}");
        httpRequest.header("Content-type", "application/json");
        Response postResponse = httpRequest.request(Method.POST, endpoint);
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    @Test
    public void createTest_2() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        "  \"name\": \"morpheus\",\n" +
                        "  \"job\": \"leader\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED);

    }

    @Test
    public void updateTest() {
        String endpoint = "/api/users/2";
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification httpRequest = given();
        httpRequest.body("{\n" +
                "  \"name\": \"morpheus\",\n" +
                "  \"job\": \"zion resident\"\n" +
                "}");
        httpRequest.header("Content-type", "application/json");
        Response postResponse = httpRequest.request(Method.PUT, endpoint);
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }


    @Test
    public void updateTest_2() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        "  \"name\": \"morpheus\",\n" +
                        "  \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void patchTest() {
        String endpoint = "/api/users/2";
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification httpRequest = given();
        httpRequest.body("{\n" +
                "  \"name\": \"morpheus\",\n" +
                "  \"job\": \"zion resident\"\n" +
                "}");
        httpRequest.header("Content-type", "application/json");
        Response postResponse = httpRequest.request(Method.PATCH, endpoint);
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void patchTest_2() {
        given()
                .body("{\n" +
                        "  \"name\": \"morpheus\",\n" +
                        "  \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void registerSuccessfulTest() {
        String endpoint = "/api/register";
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification httpRequest = given();
        httpRequest.body("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}");
        httpRequest.header("Content-type", "application/json");
        Response postResponse = httpRequest.request(Method.POST, endpoint);
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void registerSuccessfulTest_2() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void registerUnsuccessfulTest() {
        String endpoint = "/api/register";
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification httpRequest = given();
        httpRequest.body("{\n" +
                "    \"email\": \"sydney@fife\"\n" +
                "}");
        httpRequest.header("Content-type", "application/json");
        Response postResponse = httpRequest.request(Method.POST, endpoint);
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 400);
    }


    @Test
    public void registerUnsuccessfulTest_2() {
        Response response = given()
            .header("Content-type", "application/json")
            .and()
            .body("{\n" +
                    "    \"email\": \"sydney@fife\"\n" +
                    "}")
            .when()
            .post("https://reqres.in/api/register")
            .then()
            .log().all()
            .extract().response();

        Assert.assertEquals(response.statusCode(), 400);
    }


    @Test
    public void loginSuccessfulTest() {
        String endpoint = "/api/login";
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification httpRequest = given();
        httpRequest.body("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}");
        httpRequest.header("Content-type", "application/json");
        Response postResponse = httpRequest.request(Method.POST, endpoint);
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void loginSuccessfulTest_2() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void loginUnsuccessfulTest() {
        String endpoint = "/api/login";
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification httpRequest = given();
        httpRequest.body("{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}");
        httpRequest.header("Content-type", "application/json");
        Response postResponse = httpRequest.request(Method.POST, endpoint);
        String responseBody = postResponse.getBody().asString();
        String responseHead = postResponse.getHeaders().toString();
        System.out.println(responseHead);
        System.out.println(responseBody);
        int statusCode = postResponse.getStatusCode();
        Assert.assertEquals(statusCode, 400);
    }

    @Test
    public void loginUnsuccessfulTest_2() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        "    \"email\": \"peter@klaven\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 400);
    }
}

