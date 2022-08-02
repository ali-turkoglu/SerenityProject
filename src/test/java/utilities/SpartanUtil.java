package utilities;


import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SpartanUtil extends SpartanNewBase {


    public static Map<String ,Object> createPerson(){

        Map<String ,Object> person=new HashMap<>();

        Faker faker=new Faker();
        person.put("name",faker.name().fullName());
        person.put("gender",faker.demographic().sex());
        person.put("phone",faker.number().numberBetween(3000000000l,7000000000l));

        return person;
    }


    public static void createSpartan(){

        Map<String ,Object> person=createPerson();

        Response response = given()
                .auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(person)
                .when().log().all()
                .post("/spartans")
                .then()
                .statusCode(201)
                .assertThat()
                .contentType("application/json")
                .extract().response();


        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));

        System.out.println("Created one spartan with this info:\n " + person);

    }

    public static void createSpartan(Map<String ,Object> person){

        Response response = given()
                .auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(person)
                .when().log().all()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .assertThat()
                .contentType("application/json")
                .extract().response();


        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));

        System.out.println("Created one spartan with this info:\n " + person);

    }

}
