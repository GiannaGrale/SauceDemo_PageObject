package adapters;

import endpoints.SectionEndpoints;
import endpoints.SuitesEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import models.Suites;
import org.apache.http.HttpStatus;

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
}
