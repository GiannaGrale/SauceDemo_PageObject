package baseEntities;

import baseDBEntity.CustomersTableAdapter;
import baseDBEntity.ProjectsTableAdapter;
import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.DB_Project;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import services.DataBaseService;


import static io.restassured.RestAssured.given;

public class BaseApiTest {
    public DB_Project db_project;
    public DB_Project db_project2;
    public DB_Project db_project3;

    public DataBaseService dataBaseServices;

    @BeforeSuite
    public void setDataBaseServicesForProjects() {
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
    }

    @AfterTest
    public  void  tearDown(){
        dataBaseServices.closeConnection();
    }
}