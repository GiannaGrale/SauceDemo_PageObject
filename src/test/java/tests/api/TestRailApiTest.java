package tests.api;

import baseEntities.BaseApiTest;
import com.google.common.html.HtmlEscapers;
import core.ReadProperties;
import endpoints.ProjectsEndpoints;
import endpoints.UserEndpoints;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Project;
import models.ProjectTypes;
import models.TestRailUser;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestRailApiTest extends BaseApiTest {

   //Home task

    @Test
    public  void getAllProjectsTest () {
        String endpoint ="index.php?/api/v2/get_projects";
        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public  void getAllProjectsTest_2 () {
        String endpoint ="index.php?/api/v2/get_projects";
        RestAssured.baseURI = ReadProperties.getInstance().getTestRailSite();
        RequestSpecification httpRequest = given();
        Response allProjectsResponse = httpRequest.request(Method.GET, endpoint);
        System.out.println(allProjectsResponse.asString());
        int statusCode = allProjectsResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public  void getOneProjectTest () {
        int projectID = 2;
        String endpoint ="index.php?/api/v2/get_project/%s";
        given()
                .when()
                .get(String.format(endpoint, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public  void getOneProjectsTest_2 () {
        int projectID = 1;
        String endpoint ="index.php?/api/v2/get_project/%s";
        RestAssured.baseURI = ReadProperties.getInstance().getTestRailSite();
        RequestSpecification httpRequest = given();
        Response oneProjectResponse = httpRequest.request(Method.GET,String.format(endpoint, projectID));
        System.out.println(oneProjectResponse.asString());
        int statusCode = oneProjectResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public  void getAllUsers () {
        String endpoint = "index.php?/api/v2/get_users";

        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public  void getUsersDetailsTest () {
        int userID = 1;
        String  endpoint = "index.php?/api/v2/get_user/%s";

        given()
                .when()
                .get(String.format(endpoint, userID))
                .then()
                .log().body()
                .body("name", is ("Alex Tros"))
                .body("email", equalTo("atrostyanko+0606@gmail.com"))
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public  void getAllUsersDetailsTest () {
        TestRailUser user = TestRailUser.builder()
                .name("Alex Tros")
                .id(1)
                .email("atrostyanko+0606@gmail.com")
                .isActive(true)
                .build();

        given()
                .when()
                .get(UserEndpoints.GET_USERS)
                .then()
                .log().body()
                .body("get(0)name", is (user.getName()))
                .body("get(0)email", equalTo(user.getEmail()))
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public  void addProjectTest () {

        Project project = Project.builder()
                .name("Anna's API test")
                .announcement("Hey, I am a test")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();

        given()
                .body(String.format("{\n" +
                                "  \"name\": \"%s\",\n" +
                                "  \"suite_mode\": \"%d\",\n" +
                                "  \"announcement\": \"%s\"\n" +
                                "}", project.getName(), project.getSuite_mode(),
                        project.getAnnouncement()))
                        .when()
                .post(ProjectsEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }
}
