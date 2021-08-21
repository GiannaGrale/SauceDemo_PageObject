package adapters;

import endpoints.SectionEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Section;
import org.apache.http.HttpStatus;


import static io.restassured.RestAssured.given;

public class SectionAdapter extends  BaseAdapter{

    public Section addSection(Section section, int projectID) {
        Response response  = given()
                .body(section, ObjectMapperType.GSON)
                .when()
                .post(String.format(SectionEndpoints.ADD_SECTIONS,projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Section.class);
    }
}
