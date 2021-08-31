package adapters;

import com.google.gson.reflect.TypeToken;
import endpoints.CasesEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Case;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CasesAdapter extends BaseAdapter {

    public Case getOne(int caseID) {
       Response response = given()
                .when()
                .get(String.format(CasesEndpoints.GET_CASE, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
               .extract().response();
       return gson.fromJson(response.asString().trim(), Case.class);
    }

    public List <Case> getAll(int projectID, int suiteID) {
        Response response = given()
                .when()
                .get(String.format(CasesEndpoints.GET_CASES, projectID, suiteID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), new TypeToken<List<Case>>() {
        }.getType());
    }

    public List<Case> getHistory(int caseID) {
        Response response = given()
                .when()
                .get(String.format(CasesEndpoints.GET_HISTORY_CASES, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), new TypeToken<List<Case>>() {
        }.getType());
    }

    public Case add(Case cases, int sectionID) {
        Response response = given()
                .body(cases, ObjectMapperType.GSON)
                .when()
                .post(String.format(CasesEndpoints.ADD_CASE, sectionID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Case.class);
    }

    public List<Case> copyCases(Case cases, int sectionID) {
        Response response = given()
                .body(cases, ObjectMapperType.GSON)
                .when()
                .post(String.format(CasesEndpoints.COPY_CASES_TO_SECTION, sectionID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), new TypeToken<List<Case>>() {
        }.getType());
    }

    public Case update(Case cases, int caseID){
        Response response = given()
                .body(cases, ObjectMapperType.GSON)
                .when()
                .post(String.format(CasesEndpoints.UPDATE_CASE, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Case.class);
    }

    public List<Case> updateAll(Case upd, int suiteID) {
        Response response = given()
                .body(upd, ObjectMapperType.GSON)
                .when()
                .post(String.format(CasesEndpoints.UPDATE_CASES, suiteID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), new TypeToken<List<Case>>() {
        }.getType());
    }

    public List<Case> moveToSection(Case cases, int sectionID) {
        Response response = given()
                .body(cases, ObjectMapperType.GSON)
                .when()
                .post(String.format(CasesEndpoints.MOVE_CASES_TO_SECTION, sectionID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), new TypeToken<List<Case>>() {
        }.getType());

    }

    public Case delete(int caseID){
        Response response = given()
                .when()
                .post(String.format(CasesEndpoints.DELETE_TESTCASE, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Case.class);
    }

    public Case deleteCases(Case cases, int suitedID, int projectID){
        Response response = given()
                .body(cases, ObjectMapperType.GSON)
                .when()
                .post(String.format(CasesEndpoints.DELETE_CASES, suitedID, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Case.class);
    }

    public String getStringCaseIDs(int suiteID, int projectID) {
        List<Case> presentCases = new CasesAdapter().getAll(projectID, suiteID);
        for (int i = 0; i <= presentCases.size() - 1; i++) {
             return Integer.toString(presentCases.get(i).getId());
        }
        return null;
    }
}

