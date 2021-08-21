package tests.api;

import baseEntities.BaseApiTest;
import endpoints.MilestonesEndpoints;
import models.Milestone;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MilestonesApiTests extends BaseApiTest {
    private int milestoneID;

    @Test(dependsOnMethods = "getMilestonesTest")
    public void getOneMilestoneTest () {
        given()
                .when()
                .get(String.format(MilestonesEndpoints.GET_MILESTONE, milestoneID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getMilestonesTest () {
        int projectID = 291;
        given()
                .when()
                .get(String.format(MilestonesEndpoints.GET_MILESTONES, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addMilestoneTest () {
        int projectID = 291;
        Milestone milestone = Milestone.builder()
                .name("Anna's milestone")
                .description("Hey, I am a milestone")
                .build();

        milestoneID = given()
                .body(String.format("{\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"description\": \"%s\"\n" +
                        "}", milestone.getName(), milestone.getDescription()))
                .when()
                .post(String.format(MilestonesEndpoints.ADD_MILESTONE, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");
    }

    @Test (dependsOnMethods = "addMilestoneTest")
    public void updateMilestoneTest () {
        Milestone milestone = Milestone.builder()
                .name("Anna's updated milestone")
                .description("Hey, I am a new milestone")
                .build();

        given()
                .body(String.format("{\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"description\": \"%s\"\n" +
                        "}", milestone.getName(), milestone.getDescription()))
                .when()
                .post(String.format(MilestonesEndpoints.UPDATE_MILESTONE, milestoneID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test (dependsOnMethods = "updateMilestoneTest")
    public void deleteMilestoneTest () {
        given()
                .when()
                .post(String.format(MilestonesEndpoints.DELETE_MILESTONE, milestoneID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }
}
