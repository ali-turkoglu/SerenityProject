package eu8.spartan.admin;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



@SerenityTest
public class SpartanAdminGetTest {

    @BeforeAll
    public static void init(){

        baseURI="http://3.87.215.11:7000";


    }

    @Test
    public void getAllSpartan(){

        SerenityRest.given()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        System.out.println("SerenityRest.lastResponse().statusCode() = " + SerenityRest.lastResponse().statusCode());

        String name = SerenityRest.lastResponse().jsonPath().getString("name");
        System.out.println("name = " + name);

    }

    @Test
    public void getOneSpartan(){

        SerenityRest.given()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    @DisplayName("GET request with Serenity Assertion way")
    @Test
    public void test3(){

        SerenityRest.given()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}");

        // Serenity way of assertion

        Ensure.that("Status code is 200",validatableResponse -> validatableResponse.statusCode(200) );

        Ensure.that("Content-type is JSON",vRes -> vRes.contentType(ContentType.JSON));

        Ensure.that("Id is 15", vRes -> vRes.body("id",is(15)));

    }


}
