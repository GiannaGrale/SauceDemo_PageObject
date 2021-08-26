package tests.dbtest;

import baseDBEntity.ProjectsTableAdapter;
import baseEntities.BaseApiTest;
import models.DB_Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHomeTest extends BaseApiTest {

    @Test
    public void projectFullInfoTest() throws SQLException {
        ProjectsTableAdapter projectAdapter = new ProjectsTableAdapter(dataBaseServices);
        projectAdapter.addProject(db_project);
        projectAdapter.addProject(db_project2);
        projectAdapter.addProject(db_project3);

        ResultSet rs = projectAdapter.getAllProjects();
        List<DB_Project> projectList = new ArrayList<>();

        while (rs.next()) {
            DB_Project project = DB_Project.builder()
                    .projectName(rs.getString("name"))
                    .teamSize(rs.getInt("size"))
                    .is_completed(rs.getBoolean("is_completed"))
                    .build();
            projectList.add(project);
        }

        Assert.assertEquals(db_project3.teamSize, projectList.get(2).teamSize);
        Assert.assertEquals(db_project.teamSize, projectList.get(0).teamSize);
        Assert.assertEquals(db_project2.teamSize, projectList.get(1).teamSize);
    }

    @Test
    public void projectNamesTest() throws SQLException {
        ProjectsTableAdapter projectAdapter = new ProjectsTableAdapter(dataBaseServices);
        projectAdapter.addProject(db_project);
        projectAdapter.addProject(db_project2);
        projectAdapter.addProject(db_project3);

        ResultSet rs = projectAdapter.getNameProject();
        List<DB_Project> projectNames = new ArrayList<>();

        while (rs.next()) {
            DB_Project project = DB_Project.builder()
                    .projectName(rs.getString("name"))
                    .build();
            projectNames.add(project);
        }
        Assert.assertEquals(db_project3.projectName, projectNames.get(2).projectName);
        Assert.assertEquals(db_project.projectName, projectNames.get(0).projectName);
        Assert.assertEquals(db_project2.projectName, projectNames.get(1).projectName);
    }
}
