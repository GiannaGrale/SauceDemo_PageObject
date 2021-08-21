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

    @Test
    public void addProjectWithSection() {
        Project project = Project.builder()
                .name("Anna's project with sections")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();

        Project addedProject = new ProjectsAdapter().add(project);
        projectID = addedProject.getId();
        System.out.println(projectID);

        Section section = Section.builder()
                .name("This is a section")
                .build();
        Section addSections = new SectionAdapter().addSection(section, projectID);
        sectionID = addSections.getId();
        System.out.println(sectionID);
    }

    @Test
    public void addProjectsWithSuites() {
        Project project = Project.builder()
                .name("Anna's project with suites")
                .suite_mode(ProjectTypes.MULTIPLE_SUITE_MODE)
                .build();

        Project addedProject = new ProjectsAdapter().add(project);
        projectID = addedProject.getId();
        System.out.println(projectID);

        Suites suites = Suites.builder()
                .name("This is a suite")
                .build();
        Suites addSuite = new SuitesAdapter().addSuite(suites, projectID);
        suiteID = addSuite.getId();
        System.out.println(suiteID);
    }

    @Test(dependsOnMethods = "addProjectWithSection")
    public void addCaseTest() {
        Case cases = Case.builder()
                .title("This is Anna's case")
                .type_id(2)
                .priority_id(3)
                .build();
        Case addCase = new CasesAdapter().add(cases, sectionID);
        caseID = addCase.getId();
        System.out.println(addCase);
    }


    @Test(dependsOnMethods = "addCaseTest")
    public void getOneCaseTest() {
        Case oneCase = new CasesAdapter().getOne(caseID);
        System.out.println(oneCase);
    }


    @Test(dependsOnMethods = "addCaseTest")
    public void getHistoryCasesTest() {
        List<Case> historyCases = new CasesAdapter().getHistory(caseID);
        System.out.println(historyCases);
    }


    @Test(dependsOnMethods = "addCaseTest")
    public void updateCaseTest() {
        Case cases = Case.builder()
                .title("Case updated by Anna")
                .type_id(2)
                .priority_id(2)
                .build();
        Case updateCase = new CasesAdapter().update(cases, caseID);
        Assert.assertEquals("Case updated by Anna", updateCase.getTitle());
    }

    //Hardcoded.Есть трудности здесь.
    @Test
    public void getAllCasesTest() {
         projectID = 124;
         suiteID = 111;
        List<Case> allCases = new CasesAdapter().getAll(projectID, suiteID);
        System.out.println(allCases.get(0));
    }

    @Test
    public void updateCasesTest() {
        suiteID = 111;
        Case updCase = Case.builder()
                .title("New cases with adapter updated by Anna")
                .case_ids("85, 86")
                .type_id(2)
                .priority_id(2)
                .build();
        List<Case> updateCases = new CasesAdapter().updateAll(updCase, suiteID);
    }

    @Test
    public void move_cases_to_section_Test() {
        List<Integer> sectionIDs = given()
                .when()
                .get(String.format(SectionEndpoints.GET_SECTIONS, 124, 111))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");
        System.out.println(sectionIDs);

        int sectionID = sectionIDs.get(0);
        System.out.println(sectionID);

        Case cases = Case.builder()
                .case_ids("101, 102")
                .build();
        List<Case> moveCases = new CasesAdapter().moveToSection(cases, sectionID);
    }

    @Test
    public void copyCases(){
        List<Integer> sectionIDs = given()
                .when()
                .get(String.format(SectionEndpoints.GET_SECTIONS, 124, 111))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");
        System.out.println(sectionIDs);

        int sectionID = sectionIDs.get(0);
        System.out.println(sectionID);
        Case cases = Case.builder()
                .case_ids("101, 102")
                .build();
        List<Case> copyCases = new CasesAdapter().copyCases(cases, sectionID);

    }
}

