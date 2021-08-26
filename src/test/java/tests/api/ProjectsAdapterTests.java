package tests.api;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import com.google.gson.GsonBuilder;
import models.Project;
import models.ProjectTypes;
import models.TestRailUser;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;


public class ProjectsAdapterTests extends BaseApiTest {
    private int projectID;

    @Test
    public void getAllProjectsAdapterTest() {
        List<Project> projectList = new ProjectsAdapter().get();
        System.out.println(projectList.get(0));
        System.out.println(projectList.size());
    }

    @Test
    public void getOneProjectAdapterTest_1() {
        Project actualProject = new ProjectsAdapter().get(1);
        System.out.println(actualProject);
    }

    @Test(dependsOnMethods = "addProjectAdapterTest")
    public void getOneProjectAdapterTest_2() {
        Project actualProject = new ProjectsAdapter().get(projectID);
        System.out.println(actualProject);
    }

    @Test
    public void getAllUsersAdapterTest() {
        List<Project> usersOfProject = new ProjectsAdapter().getUsers();
        System.out.println(usersOfProject.get(1).getId());
    }

    @Test
    public void getAllUsersDetailsAdapterTest() {
        List<TestRailUser> dataUsers = new ProjectsAdapter().getAllUsersDetailsTest();
        System.out.println(dataUsers.get(1));
    }

    @Test
    public void addProjectAdapterTest() {
        Project project = Project.builder()
                .name("Anna's Adapter API test")
                .announcement("Hey, I am a test")
                .suite_mode(ProjectTypes.MULTIPLE_SUITE_MODE)
                .build();

        Project addedProject = new ProjectsAdapter().post(project);
        System.out.println(addedProject);

        projectID = new ProjectsAdapter().add(addedProject).getId();
    }

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

    @Test (dependsOnMethods = "updateProjectAdapterTest")
    public void deleteAdapterTest() {
        Project deleteProject = new ProjectsAdapter().delete(projectID);
        System.out.println(deleteProject);
    }

    @Test
    public void staticJsonValidationTest() throws FileNotFoundException {
        Project expectedProject = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                .fromJson(new FileReader("src/test/resources/expectedProject.json"), Project.class);
        Project actualProject = new ProjectsAdapter().get(1);
        Assert.assertEquals(actualProject.getName(), expectedProject.getName());
    }
}


