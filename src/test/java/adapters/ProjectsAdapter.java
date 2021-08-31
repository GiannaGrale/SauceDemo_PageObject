package adapters;

import com.google.gson.reflect.TypeToken;
import endpoints.ProjectsEndpoints;
import endpoints.UserEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import models.TestRailUser;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;


public class ProjectsAdapter extends BaseAdapter {

    public List<Project> get() {
        Response response = given()
                .when()
                .get(ProjectsEndpoints.GET_PROJECTS)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), new TypeToken<List<Project>>() {
        }.getType());

    }

    public Project get(int projectID) {
        Response response = given()
                .when()
                .get(String.format(ProjectsEndpoints.GET_PROJECT, projectID))
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), Project.class);

    }

    public Project post(Project project) {
        Response response = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(ProjectsEndpoints.ADD_PROJECT)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), Project.class);
    }


    public List<Project> getUsers() {
        Response response = given()
                .when()
                .get(UserEndpoints.GET_USERS)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), new TypeToken<List<Project>>() {
        }.getType());
    }


    public List<TestRailUser> getAllUsersDetailsTest() {
        Response response = given()
                .when()
                .get(UserEndpoints.GET_USERS)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), new TypeToken<List<TestRailUser>>() {
        }.getType());
    }

    public Project update(Project project, int projectID) {
        Response response = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(String.format(ProjectsEndpoints.UPDATE_PROJECT, projectID))
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Project.class);
    }

    public Project add(Project project) {
        Response response = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(ProjectsEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Project.class);

    }

    public Project delete(int projectID) {
        Response response = given()
                .post(String.format(ProjectsEndpoints.DELETE_PROJECT, projectID))
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Project.class);
    }

    public int projectSearch(String name) {
        for (Project expectedProject : get()) {
            if (expectedProject.getName().equals(name))
                return expectedProject.getId();
        }
        return 0;
    }
}

