package baseEntities;

import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseApiTest {

    @BeforeTest
    public void setup (){
        RestAssured.baseURI = ReadProperties.getInstance().getTestRailSite();
        RestAssured.requestSpecification = given()
           .header(HTTP.CONTENT_TYPE, ContentType.JSON)
           .auth().preemptive().basic
                (ReadProperties.getInstance().getLogin(),
                        ReadProperties.getInstance().getPassword());
    }

}
