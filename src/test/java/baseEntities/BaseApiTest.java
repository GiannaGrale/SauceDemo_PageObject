package baseEntities;

import baseDBEntity.ProjectsTableAdapter;
import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.DB_Project;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import services.DataBaseService;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BaseApiTest {
    public DB_Project db_project;
    public DB_Project db_project2;
    public DB_Project db_project3;
    public List<DB_Project> projectNames = new ArrayList<>();
    public DataBaseService dataBaseServices;

    @BeforeSuite
    public void setDataBaseServicesForProjects() throws SQLException {
        RestAssured.baseURI = ReadProperties.getInstance().getTestRailSite();
        RestAssured.requestSpecification = given()
           .header(HTTP.CONTENT_TYPE, ContentType.JSON)
           .auth().preemptive().basic
                (ReadProperties.getInstance().getLogin(),
                        ReadProperties.getInstance().getPassword());
        dataBaseServices = new DataBaseService();
        ProjectsTableAdapter projectsTableAdapter = new ProjectsTableAdapter(dataBaseServices);
        projectsTableAdapter.dropProjectTable();
        projectsTableAdapter.createProjectTable();

         db_project = DB_Project.builder()
                .projectName("SauceDemo")
                .is_completed(true)
                .teamSize(5)
                .build();

         db_project2 = DB_Project.builder()
                .projectName("TestRail")
                .is_completed(false)
                .teamSize(3)
                .build();

         db_project3 = DB_Project.builder()
                .projectName("Qase")
                .is_completed(false)
                .teamSize(8)
                .build();

        ProjectsTableAdapter projectAdapter = new ProjectsTableAdapter(dataBaseServices);
        projectAdapter.addProject(db_project);
        projectAdapter.addProject(db_project2);
        projectAdapter.addProject(db_project3);

        ResultSet rs = projectAdapter.getNameProject();

        while (rs.next()) {
            DB_Project project = DB_Project.builder()
                    .projectName(rs.getString("name"))
                    .build();
            projectNames.add(project);
        }

    }

    @AfterTest
    public  void  tearDown(){
        dataBaseServices.closeConnection();
    }
}