package com.codeaman.restassured;

import com.codeaman.restassured.domain.PetsDetails;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

/**
 * In this class, we are doing deserialization of response.
 */
public class Test7_DeserializeResponse {

    /**
     * In this method, we are just validating new API status in this program.
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

    /**
     * In this method, we are extracting Json response in Java object.
     * Which is known as deserialization. Also, validating it.
     */
    @Test
    public void testDeserializeAPIResponse(){

        PetsDetails petsDetails = given().get("https://petstore.swagger.io/v2/pet/1").
                then().assertThat().extract().as(PetsDetails.class);

        assertEquals(petsDetails.getId(), 1);
        assertEquals(petsDetails.getCategory().getName(), "string");
        assertEquals(petsDetails.getName(), "doggie");
        assertEquals(petsDetails.getTags().get(0).getName(), "string");
        assertEquals(petsDetails.getStatus(), "available");
    }
}
