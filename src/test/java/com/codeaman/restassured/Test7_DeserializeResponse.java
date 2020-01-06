package com.codeaman.restassured;

import com.codeaman.restassured.domain.PetsDetails;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Test7_DeserializeResponse {

    /*
     *
     */
    @Test
    public void testAPI(){
        given().
                baseUri("https://petstore.swagger.io/v2").
                basePath("/pet").
        when().
                get("/537").
        then().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(200);
    }

    @Test
    public void testDeserializeAPIResponse(){

        PetsDetails petsDetails = given().get("https://petstore.swagger.io/v2/pet/537").
                then().assertThat().extract().as(PetsDetails.class);

        assertEquals(petsDetails.getId(), 537);
        assertEquals(petsDetails.getCategory().getName(), "cat-family");
        assertEquals(petsDetails.getName(), "Kitty");
        assertEquals(petsDetails.getTags().get(0).getName(), "cat");
        assertEquals(petsDetails.getStatus(), "pending");
    }
}
