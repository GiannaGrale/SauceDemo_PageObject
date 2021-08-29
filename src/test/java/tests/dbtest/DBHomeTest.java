package tests.dbtest;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DBHomeTest extends BaseApiTest {

    @Test
    public void testRailAPI_BD_Test() {
        Project testRail = new ProjectsAdapter().get(81);
        Project railProject1 = Project.builder()
                .name(testRail.getName())
                .is_completed(testRail.is_completed())
                .build();

        Assert.assertEquals(railProject1.getName(), projectNames.get(1).projectName);
    }

    @Test
    public void qaseAPI_BD_Test() {
        Project qase = new ProjectsAdapter().get(82);
        Project railProject2 = Project.builder()
                .name(qase.getName())
                .is_completed(qase.is_completed())
                .build();

        Assert.assertEquals(railProject2.getName(), projectNames.get(2).projectName);
    }

    @Test
    public void sauceDemoAPI_BD_Test() {
        Project sauceDemo = new ProjectsAdapter().get(83);
        Project railProject3 = Project.builder()
                .name(sauceDemo.getName())
                .is_completed(sauceDemo.is_completed())
                .build();

        Assert.assertEquals(railProject3.getName(), projectNames.get(0).projectName);

    }
}