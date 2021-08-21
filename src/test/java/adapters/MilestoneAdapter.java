package adapters;


import com.google.gson.reflect.TypeToken;
import endpoints.MilestonesEndpoints;
import endpoints.ProjectsEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Milestone;
import org.apache.http.HttpStatus;


import java.util.List;

import static io.restassured.RestAssured.given;

public class MilestoneAdapter extends BaseAdapter {

    public Milestone get(int milestoneID) {
        Response response = given()
                .when()
                .get(String.format(ProjectsEndpoints.GET_PROJECT, milestoneID))
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), Milestone.class);
    }

    public List<Milestone> getList(int projectID) {
        Response response = given()
                .when()
                .get(String.format(MilestonesEndpoints.GET_MILESTONES, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), new TypeToken<List<Milestone>>(){
        }.getType());
    }

    public Milestone post(Milestone milestone, int projectID) {
        Response response = given()
                .body(milestone, ObjectMapperType.GSON)
                .when()
                .post(String.format(MilestonesEndpoints.ADD_MILESTONE, projectID))
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), Milestone.class);
    }


    public Milestone delete (int milestoneID) {
        Response response = given()
                .when()
                .post(String.format(MilestonesEndpoints.DELETE_MILESTONE, milestoneID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), Milestone.class);
    }

    public Milestone update(Milestone milestone, int milestoneID) {
        Response response = given()
                .body(milestone, ObjectMapperType.GSON)
                .when()
                .post(String.format(MilestonesEndpoints.UPDATE_MILESTONE, milestoneID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Milestone.class);
    }
}
