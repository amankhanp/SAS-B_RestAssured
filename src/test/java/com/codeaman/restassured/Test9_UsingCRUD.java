package com.codeaman.restassured;

import com.codeaman.restassured.domain.Categories;
import com.codeaman.restassured.domain.PetsDetails;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

/**
 * In this class, we are using CRUD, which stands for C-Create(Post), R-Read(get), U-Update(Put) and D-Delete(delete).
 */
public class Test9_UsingCRUD {

    /**
     * In Before class method, we are giving baseURI, basePath and requestSpecification.
     */
    @BeforeClass
    public void testSetUp() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setContentType("application/json").build();
    }

    /**
     * In this method, created 2 objects related to Pet.
     * and in given body session we are passing pet object when we are posting a new request.
     * post method will take the request from body and adds a new pet to the store as per API.
     * in this serialization take place.
     * using 'post' method - we 'create' a new request. CRUD
     */
    @Test()
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

    /**
     * In this method, we are using put method to update the request.
     * using 'put' method - we 'update' a request. CRUD
     */
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

    /**
     * In this method, as we see in previous methods we have used given, when method to set
     * baseURL, basePath or RequestSpecification but now in this class we have declared all the
     * common details in beforeClass method,so we have started this method from static get method.
     * using 'get' method - we 'read' a request. CRUD
     */
    @Test(priority = 2)
    public void testUsingGet() {
        get("/pet/537").
         then().
                assertThat().
                statusCode(200).
                log().all();
    }

    /**
     * In this method we are just deleting the pet details which we have created using post method.
     * using 'delete' method - we 'delete' a request. CRUD
     */
    @Test(priority = 3)
    public void testCaseDelete() {
        delete("/pet/537").
        then().
                assertThat().statusCode(200);
    }
}
