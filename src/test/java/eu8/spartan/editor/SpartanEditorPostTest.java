package eu8.spartan.editor;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanNewBase;
import utilities.SpartanUtil;

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
        Ensure.that("name is correct",x->x.body("data.gender",is(bodyMap.get("gender"))));
        Ensure.that("name is correct",x->x.body("data.phone",is(bodyMap.get("phone"))));


        //String id= lastResponse().jsonPath().getString("data.id");
        //System.out.println("id = " + id);









    }

}
