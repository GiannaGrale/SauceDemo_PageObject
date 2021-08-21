package tests.api;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import com.google.gson.GsonBuilder;
import endpoints.ProjectsEndpoints;
import io.restassured.mapper.ObjectMapperType;
import models.Project;
import models.ProjectTypes;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ChainsOfTests extends BaseApiTest {
    private int projectID;

    @Test
    public  void addProjectTest4 () {
        Project project = Project.builder()
                .name("Anna's API test")
                .announcement("Hey, I am a test")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();

     //   System.out.println(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(project));

        projectID =  given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(ProjectsEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");
    }

    //Adapter
    @Test
    public void addNewProjectAdapterTest () {
        Project newProject = Project.builder()
                .name("Anna's API test")
                .announcement("Hey, I am a test")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();
        Project addProject = new ProjectsAdapter().add(newProject);
        projectID = addProject.getId();

    }

    @Test(dependsOnMethods = "addProjectTest4")
    public void updateProjectTest () {
        Project projectUpdate = Project.builder()
                .name("Anna's API test_3")
                .announcement("Hey, I am a test")
                .is_completed(true)
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();

        String json = given()
                .body(projectUpdate, ObjectMapperType.GSON)
                .when()
                .post(String.format(ProjectsEndpoints.UPDATE_PROJECT, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().asString();
        Project newProject = new GsonBuilder().create().fromJson(json, Project.class);

        Assert.assertTrue(projectUpdate.getName().equals(newProject.getName()));
    }

    @Test(dependsOnMethods = "addNewProjectAdapterTest")
    public void updateProjectAdapterTest () {
        Project projectUpdate = Project.builder()
                .name("Anna's API test_Adapter")
                .announcement("Hey, I am a test")
                .is_completed(true)
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();

        Project updatedProject = new ProjectsAdapter().update(projectUpdate,projectID);
        System.out.println(updatedProject);
    }

    @Test (dependsOnMethods = "updateProjectTest")
    public void deleteTest(){
        given()
                .post(String.format(ProjectsEndpoints.DELETE_PROJECT ,projectID))
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test (dependsOnMethods = "addNewProjectAdapterTest")
    public void deleteAdapterTest(){
        Project deleteProject = new ProjectsAdapter().delete(projectID);
        System.out.println(deleteProject);
    }
}
