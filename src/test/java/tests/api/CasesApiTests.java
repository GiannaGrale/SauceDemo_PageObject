package tests.api;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import endpoints.CasesEndpoints;
import models.Case;
import models.Project;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.given;

public class CasesApiTests extends BaseApiTest {
    private int caseID;
    private int projectID;
    private int suiteID;
    private int sectionID;

    private List<Integer> suiteIDs;

    @Test(dependsOnMethods = "addCaseTest")
    public void getOneCaseTest() {
        given()
                .when()
                .get(String.format(CasesEndpoints.GET_CASE, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getCasesTest() {
        projectID = 18;
        suiteID = 1;

        Project project = Project.builder()
                .name("Anna's API test")
                .build();

        given()
                .when()
                .get(String.format(CasesEndpoints.GET_CASES, projectID, suiteID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = "addCaseTest")
    public void getHistoryCasesTest() {
        given()
                .when()
                .get(String.format(CasesEndpoints.GET_HISTORY_CASES, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test()
    public void addCaseTest() {
        sectionID = 1;
        Case cases = Case.builder()
                .title("Case added by Anna")
                .type_id(2)
                .build();

        caseID = given()
                .body(String.format("{\n" +
                        "  \"title\": \"%s\",\n" +
                        "  \"type_id\": \"%d\"\n" +
                        "}", cases.getTitle(), cases.getType_id()))
                .when()
                .post(String.format(CasesEndpoints.ADD_CASE, sectionID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");
    }


    @Test(priority = 1, dependsOnMethods = "addCaseTest")
    public void updateCaseTest() {
        Case cases = Case.builder()
                .title("Case updated by Anna")
                .type_id(2)
                .build();

        given()
                .body(String.format("{\n" +
                        "  \"title\": \"%s\",\n" +
                        "  \"type_id\": \"%d\"\n" +
                        "}", cases.getTitle(), cases.getType_id()))
                .when()
                .post(String.format(CasesEndpoints.UPDATE_CASE, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void updateCasesTest() {
        suiteID = 16;
        Case cases = Case.builder()
                .title("All cases updated by Anna")
                .case_ids("6, 8")
                .build();

        given()
                .body(String.format("{\n" +
                        "  \"title\": \"%s\",\n" +
                        "  \"case_ids\": \"%s\"\n" +
                        "}", cases.getTitle(), cases.getCase_ids()))
                .when()
                .post(String.format(CasesEndpoints.UPDATE_CASES, suiteID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void move_cases_to_section_Test() {
        sectionID = 1;
        Case cases = Case.builder()
                .case_ids("4, 5")
                .build();

        given()
                .body(String.format("{\n" +
                        "  \"case_ids\": \"%s\"\n" +
                        "}", cases.getCase_ids()))
                .when()
                .post(String.format(CasesEndpoints.MOVE_CASES_TO_SECTION, sectionID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(priority = 2, dependsOnMethods = "updateCaseTest")
    public void deleteCaseTest() {
        given()
                .when()
                .post(String.format(CasesEndpoints.DELETE_TESTCASE, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void copyCasesToSectionTest() {
        sectionID = 2;
        Case cases = Case.builder()
                .case_ids("116, 117")
                .build();

        given()
                .body(String.format("{\n" +
                        "  \"case_ids\": \"%s\"\n" +
                        "}", cases.getCase_ids()))
                .when()
                .post(String.format(CasesEndpoints.COPY_CASES_TO_SECTION, sectionID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

}
