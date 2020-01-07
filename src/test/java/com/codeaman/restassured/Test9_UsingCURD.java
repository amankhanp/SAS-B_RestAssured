package com.codeaman.restassured;

import com.codeaman.restassured.domain.Categories;
import com.codeaman.restassured.domain.PetsDetails;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Test9_UsingCURD {

    @BeforeClass
    public void testSetUp(){
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";

        RestAssured.requestSpecification = new RequestSpecBuilder().
                setContentType("application/json").build();
    }

    @Test(priority = 0)
    public void testUsingPost() {
        Categories categoryDetails = new Categories(1, "Cat-Family");
        PetsDetails petsDetails = new PetsDetails(537, categoryDetails, "Jacky", "pending");
        given().
                body(petsDetails).
        when().
                post("/pet").
        then().
                assertThat().statusCode(200);
    }

    @Test(priority = 1)
    public void testUsingPut() {
        Categories categoryDetails = new Categories(1, "Cat-Family");
        PetsDetails petsDetails = new PetsDetails(537, categoryDetails, "Kitty", "available");
        given().
                body(petsDetails).
        when().
                post("/pet").
        then().
                assertThat().statusCode(200);
    }

    @Test(priority = 2)
    public void testUsingGet() {
        get("/pet/537").
        then().
                assertThat().
                statusCode(200).
                log().all();
    }

    @Test(priority = 3)
    public void testCaseDelete(){
        delete("/pet/537").
        then().
                assertThat().statusCode(200);
    }
}
