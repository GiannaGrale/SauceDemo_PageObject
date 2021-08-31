package tests.api;

import adapters.CasesAdapter;
import adapters.ProjectsAdapter;
import adapters.SectionAdapter;
import adapters.SuitesAdapter;
import baseEntities.BaseApiTest;
import endpoints.CasesEndpoints;
import endpoints.SectionEndpoints;
import models.Case;
import models.Project;
import models.Suites;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.given;

public class CasesApiTests extends BaseApiTest {
    private int caseID;
    private int projectID;
    private int suiteID;
    private int sectionID;
    String projectWithSuite = "Anna's Adapter API test (1 suite)";
    String projectWithSection = "Anna's Adapter API test (2 cases)";
    String suiteName = "Test Suite _1";
    String sectionName = "This is a new section";


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
        projectID = new ProjectsAdapter().projectSearch(projectWithSuite);
        suiteID = new SuitesAdapter().suiteSearch(suiteName, projectID);


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
        projectID = new ProjectsAdapter().projectSearch(projectWithSection);
        sectionID = new SectionAdapter().sectionSearch(sectionName, projectID);
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
        projectID = new ProjectsAdapter().projectSearch(projectWithSuite);
        suiteID = new SuitesAdapter().suiteSearch(suiteName, projectID);

        String definedCaseID = new CasesAdapter().getStringCaseIDs(suiteID, projectID);
        Case cases = Case.builder()
                .title("All cases updated by Anna")
                .case_ids(definedCaseID)
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
        projectID = new ProjectsAdapter().projectSearch(projectWithSection);
        sectionID = new SectionAdapter().sectionSearch(sectionName, projectID);
        Case cases = Case.builder()
                .case_ids("316, 313")
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
        projectID = new ProjectsAdapter().projectSearch(projectWithSection);
        sectionID = new SectionAdapter().sectionSearch(sectionName, projectID);
        System.out.println(sectionID);

        List<Integer> sectionIDs = given()
                .when()
                .get(String.format(SectionEndpoints.GET_SECTIONS, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");

        sectionID = sectionIDs.get(2);
        Suites suiteIDs = new SuitesAdapter().getSuite(projectID).get(0);
        int actualSuiteID = suiteIDs.getId();

        String definedCaseID = new CasesAdapter().getStringCaseIDs(actualSuiteID, projectID);

        Case cases = Case.builder()
                .case_ids(definedCaseID)
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
