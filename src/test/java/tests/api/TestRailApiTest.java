package tests.api;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import com.google.gson.GsonBuilder;
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
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestRailApiTest extends BaseApiTest {

    @Test
    public  void getAllProjectsTest () {
        given()
                .when()
                .get(ProjectsEndpoints.GET_PROJECTS)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }


    @Test
    public void getAllProjectsTest_2 () {
        RestAssured.baseURI = ReadProperties.getInstance().getTestRailSite();
        RequestSpecification httpRequest = given();
        Response allProjectsResponse = httpRequest.request(Method.GET, ProjectsEndpoints.GET_PROJECTS);
        System.out.println(allProjectsResponse.asString());
        int statusCode = allProjectsResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public  void getOneProjectTest () {
        int projectID = 2;
        given()
                .when()
                .get(String.format(ProjectsEndpoints.GET_PROJECT, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getOneProjectTest_2_2 () {
        int projectID = 1;
        RestAssured.baseURI = ReadProperties.getInstance().getTestRailSite();
        RequestSpecification httpRequest = given();
        Response oneProjectResponse = httpRequest.request(Method.GET,String.format(ProjectsEndpoints.GET_PROJECT, projectID));
        System.out.println(oneProjectResponse.asString());
        int statusCode = oneProjectResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }


    @Test
    public  void getAllUsers () {
        given()
                .when()
                .get(UserEndpoints.GET_USERS)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public  void getUsersDetailsTest () {
        int userID = 1;
        given()
                .when()
                .get(String.format(UserEndpoints.GET_USER, userID))
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


    @Test
    public  void addProjectTest2 () {
        Project project = Project.builder()
                .name("Anna's API test")
                .announcement("Hey, I am a test")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", project.getName());
        jsonAsMap.put ("suite_mode", project.getSuite_mode());

        given()
                .body(jsonAsMap)
                .when()
                .post(ProjectsEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }


    @Test
    public  void addProjectTest3 () {
        Project project = Project.builder()
                .name("Anna's API test")
                .announcement("Hey, I am a test")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();

        given()
                .body(project)
                .when()
                .post(ProjectsEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }
}
