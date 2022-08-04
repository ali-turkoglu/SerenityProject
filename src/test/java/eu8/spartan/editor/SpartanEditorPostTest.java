package eu8.spartan.editor;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanNewBase;
import utilities.SpartanUtil;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.SpartanNewBase;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.rest.SerenityRest.given;

@Disabled
@SerenityTest
public class SpartanEditorPostTest extends SpartanNewBase {


    @DisplayName("Editor should be able to POST")
    @Test
    public void postSpartanAsEditor(){

        // create one spartan

        Map<String ,Object> bodyMap= SpartanUtil.createPerson();

        System.out.println("bodyMap = " + bodyMap);

        // send a post request as editor
        SpartanUtil.createSpartan(bodyMap);

        Ensure.that("status code is 201", vRes -> vRes.statusCode(201));

        Ensure.that("contentType is Json", x -> x.contentType(ContentType.JSON));

        Ensure.that("success message is A Spartan is Born!",x->x.body("success",is("A Spartan is Born!")));

        Ensure.that("id is not null",x->x.body("data.id",notNullValue()));

        Ensure.that("name is correct",x->x.body("data.name",is(bodyMap.get("name"))));
        Ensure.that("gender is correct",x->x.body("data.gender",is(bodyMap.get("gender"))));
        Ensure.that("phone is correct",x->x.body("data.phone",is(bodyMap.get("phone"))));


        //String id= lastResponse().jsonPath().getString("data.id");
        //System.out.println("id = " + id);

    }

    @ParameterizedTest(name="New Spartan {index}-name: {0}")
    @CsvFileSource(resources= "/SpartanData.csv",numLinesToSkip = 1)
      public void postSpartanWithCSV(String name,String gender,long phone){

        Map<String ,Object> bodyMap=new HashMap<>();
        bodyMap.put("name",name);
        bodyMap.put("gender",gender);
        bodyMap.put("phone",phone);

        SerenityRest.given()
                .auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when().log().all()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .assertThat()
                .contentType("application/json");

        Ensure.that("name is true",validatableResponse -> validatableResponse
                .body("name",is(bodyMap.get("name"))));

    }


}
