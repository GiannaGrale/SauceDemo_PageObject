package adapters;

import com.google.gson.reflect.TypeToken;
import endpoints.SectionEndpoints;
import endpoints.SuitesEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import models.Milestone;
import models.Section;
import models.Suites;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SuitesAdapter extends BaseAdapter{

    public Suites addSuite(Suites suites, int projectID) {
        Response response  = given()
                .body(suites, ObjectMapperType.GSON)
                .when()
                .post(String.format(SuitesEndpoints.ADD_SUITE, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Suites.class);
    }

    public List<Suites> getSuite(int projectID) {
        Response response = given()
                .when()
                .get(String.format(SuitesEndpoints.GET_SUITES, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), new TypeToken<List<Suites>>() {
        }.getType());
    }

    public int suiteSearch(String name, int projectID) {
        for (Suites expectedSuite : getSuite(projectID)) {
            if (expectedSuite.getName().equals(name))
                return expectedSuite.getId();
        }
        return 0;
    }
}
