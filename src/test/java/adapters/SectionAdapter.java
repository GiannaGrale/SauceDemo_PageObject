package adapters;

import com.google.gson.reflect.TypeToken;
import endpoints.SectionEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Section;
import org.apache.http.HttpStatus;


import java.util.List;

import static io.restassured.RestAssured.given;

public class SectionAdapter extends  BaseAdapter {

    public Section addSection(Section section, int projectID) {
        Response response = given()
                .body(section, ObjectMapperType.GSON)
                .when()
                .post(String.format(SectionEndpoints.ADD_SECTIONS, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Section.class);
    }

    public List<Section> getSections(int projectID) {
        Response response = given()
                .when()
                .get(String.format(SectionEndpoints.GET_SECTIONS, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), new TypeToken<List<Section>>() {
        }.getType());
    }

    public int sectionSearch(String name, int projectID) {
        for (Section expectedSection : getSections(projectID)) {
            if (expectedSection.getName().equals(name))
                return expectedSection.getId();
        }
        return 0;
    }
}
