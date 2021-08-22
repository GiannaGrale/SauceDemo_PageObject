package tests.api;

import adapters.CasesAdapter;
import adapters.ProjectsAdapter;
import adapters.SectionAdapter;
import adapters.SuitesAdapter;
import baseEntities.BaseApiTest;
import endpoints.SectionEndpoints;
import models.*;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CasesAdapterTests extends BaseApiTest {
    private int caseID;
    private int projectID;
    private int suiteID;
    private int sectionID;
    String suiteName = "Test Suite _1";
    String sectionName = "This is a new section";
    String projectNameWithSuites = "Anna's Adapter API test (1 suite)";
    String projectNameWithSections =  "Anna's Adapter API test (2 cases)";

    @Test
    public void createSuitesTest() {
        projectID = new ProjectsAdapter().projectSearch(projectNameWithSuites);
        Suites suites = Suites.builder()
                .name(suiteName)
                .build();
        Suites addSuite = new SuitesAdapter().addSuite(suites, projectID);
        suiteID = addSuite.getId();
    }

    @Test
    public void addCaseInSectionTest() {
        projectID = new ProjectsAdapter().projectSearch(projectNameWithSections);

        Section section = Section.builder()
                .name(sectionName)
                .build();

        Section addSection = new SectionAdapter().addSection(section, projectID);
        sectionID = new SectionAdapter().sectionSearch(sectionName, projectID);

        Case cases = Case.builder()
                .title("This is Anna's new case with SECTION")
                .type_id(CaseTypes.TYPE_AUTOMATED)
                .priority_id(CasePriority.PRIORITY_MEDIUM)
                .build();
        Case addCase = new CasesAdapter().add(cases, sectionID);
        caseID = addCase.getId();
        System.out.println(addCase);
    }

    @Test(dependsOnMethods = "addCaseInSectionTest")
    public void getOneCaseTest() {
        Case oneCase = new CasesAdapter().getOne(caseID);
        System.out.println(oneCase);
    }

    @Test(dependsOnMethods = "addCaseInSectionTest")
    public void getHistoryOfCasesTest() {
        List<Case> historyCases = new CasesAdapter().getHistory(caseID);
        System.out.println(historyCases);
    }

    @Test(dependsOnMethods = "addCaseInSectionTest")
    public void changeCaseTest() {
        Case cases = Case.builder()
                .title("Case updated by Anna")
                .type_id(CaseTypes.TYPE_COMPATIBILITY)
                .priority_id(CasePriority.PRIORITY_CRITICAL)
                .build();
        Case updateCase = new CasesAdapter().update(cases, caseID);
        Assert.assertEquals("Case updated by Anna", updateCase.getTitle());
    }

    @Test(dependsOnMethods = "createSuitesTest")
    public void getAllCasesTest() {
        suiteID = new SuitesAdapter().suiteSearch(suiteName, projectID);
        List<Case> allCases = new CasesAdapter().getAll(projectID, suiteID);
        System.out.println(allCases.get(3));
    }

    //Hardcoded case_ids. Есть трудности здесь.

    @Test(dependsOnMethods = "createSuitesTest")
    public void updateCasesTest() {
        suiteID = new SuitesAdapter().suiteSearch(suiteName, projectID);
        Case updCase = Case.builder()
                .title("New UPDATED cases with adapter created by Anna")
                .case_ids("85, 86")
                .type_id(CaseTypes.TYPE_REGRESSION)
                .priority_id(CasePriority.PRIORITY_HIGH)
                .build();
        List<Case> updateCases = new CasesAdapter().updateAll(updCase, suiteID);
    }

    @Test(dependsOnMethods = "addCaseInSectionTest")
    public void cases_to_new_section_Test() {
        List<Integer> sectionIDs = given()
                .when()
                .get(String.format(SectionEndpoints.GET_SECTIONS, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");


        sectionID = sectionIDs.get(4);

        Case cases = Case.builder()
                .case_ids("94, 95")
                .build();
        List<Case> moveCases = new CasesAdapter().moveToSection(cases, sectionID);
    }

    @Test(dependsOnMethods = "addCaseInSectionTest")
    public void copyCases() {
        List<Integer> sectionIDs = given()
                .when()
                .get(String.format(SectionEndpoints.GET_SECTIONS, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");

         sectionID = sectionIDs.get(2);

        Case cases = Case.builder()
                .case_ids("326, 327")
                .build();
        List<Case> copyCases = new CasesAdapter().copyCases(cases, sectionID);
    }
}

