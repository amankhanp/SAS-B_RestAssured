package com.codeaman.restassured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * In this class we are simply checking status code using Rest Assured for JSON.
 */
public class Test1_BasicFeaturesForJSON {

    /**
     * Simply we are hitting given api ("https://jsonplaceholder.typicode.com/posts/3").
     * and then validating its response. That's all with this method.
     * (static import io.restassured.RestAssured.*) -> given(), then().
     */
    @Test
    public void testStatusCode(){
        given().get("https://jsonplaceholder.typicode.com/posts/3").
        then().statusCode(200);
    }

    /**
     * Simply we are hitting given api ("https://jsonplaceholder.typicode.com/posts/3").
     * and then validating its response and also printing the complete response in console.
     */
    @Test
    public void testLogging(){
        given().get("https://jsonplaceholder.typicode.com/posts/3").
        then().statusCode(200).log().all();
    }

    /**
     * Simply we are hitting given api ("https://jsonplaceholder.typicode.com/posts/2")
     * then verifying single content using org.hamcrest.Matchers library's equalTo method/function.
     */
    @Test
    public void testEqualToFunction(){
        given().get("https://jsonplaceholder.typicode.com/posts/2").
        then().body("title", equalTo("qui est esse"));
    }

    /**
     * Simply we are hitting given api ("https://jsonplaceholder.typicode.com/posts")
     * then verifying multiple content using org.hamcrest.Matchers library's hasItems method/function.
     */
    @Test
    public void testHasItemFunction(){
        given().get("https://jsonplaceholder.typicode.com/posts").
        then().body("id", hasItems(2,10,99));
    }

    /**
     * Simply we are hitting given params, header.
     * when calling api ("https://jsonplaceholder.typicode.com/posts/1")
     * then validating its status code also printing its logs.
     * (static import io.restassured.RestAssured.*) -> given(), when() and then().
     */
    @Test
    public void testParamsAndHeaders(){
        given().
                param("key1", "value1").header("headA","valueA").
        when().
                get("https://jsonplaceholder.typicode.com/posts/1").
        then().
                statusCode(200).log().all();
    }

    /**
     * Simply we are hitting given params, header.
     * when calling api ("https://jsonplaceholder.typicode.com/posts/1")
     * And using 'and' to increase readability, generally used when writing in one line xpath style.
     * without 'and' it doesn't make any difference its simply adds readability.
     * And when calling api ("https://jsonplaceholder.typicode.com/posts/1")
     * then validating its status code also printing its logs.
     */
    @Test
    public void testAndFeatureForReadability    (){
        given().param("key1", "value1").and().header("headA","valueA").when().get("https://jsonplaceholder.typicode.com/posts/1").then().statusCode(200).and().body("id", equalTo(1));
    }

}
