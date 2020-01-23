package com.codeaman.restassured;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Example1: http://api.fonts.com/rest/req?key1=val1!key2=val2, if we see
 * at the end of the given url there is something like req?key1=val1 those
 * are queries for validating them we use 'queryParam','formParam','param'.
 *
 * Example2: http://api.fonts.com/rest/{type}/{section}/, if we see at the
 * end of the given url there is something like /{type}/{section}/ those are
 * path's for validating them we use 'pathParam'.
 */
public class Test12_Params {

    /**
     * Query Param: if the request is 'GET' then 'queryParam' need to be use.
     */
    @Test
    public void testQueryParams(){
        given().
                queryParam("A", "A Val").
                queryParam("B", "B Val").
        when().
                get("http://api.fonts.com/rest/json/Accounts/").
        then().
                statusCode(400);
    }

    /**
     * Form Param: if the request is 'POST' then 'formParam' need to be use.
     */
    @Test
    public void testFormParams(){
        given().
                formParam("A", "A Val").
                formParam("B", "B Val").
        when().
                post("http://api.fonts.com/rest/json/Accounts/").
        then().
                statusCode(400);
    }

    /**
     * Param: 'param' is a recommended way to use if request can be any type.
     */
    @Test
    public void testParams(){
        given().
                param("A", "A Val").
                param("B", "B Val").
        when().
                get("http://api.fonts.com/rest/json/Accounts/").
        then().
                statusCode(400);
    }

    /**
     * Path Param: for setting path we use 'pathParam'.
     */
    @Test
    public void testPathParams(){
        given().
                pathParam("type", "json").
                pathParam("section", "Domains").
        when().
                get("http://api.fonts.com/rest/{type}/{section}/").
        then().
                statusCode(400);
    }

    @Test
    public void testBodyParamsUsing7(){
        //validating body response using Java 7
        given().
                get("https://jsonplaceholder.typicode.com/photos/1").
        then().
                body("thumbnailUrl", new ResponseAwareMatcher<Response>() {
                    @Override
                    public Matcher<?> matcher(Response response) throws Exception {
                        return equalTo("https://via.placeholder.com/150/92c952");
                    }
                });
    }

    @Test
    public void testBodyParamsUsing8(){
        //validating body response using Java 8 -> lambda expression
        given().
                get("https://jsonplaceholder.typicode.com/photos/1").
        then().
                body("thumbnailUrl", response -> equalTo("https://via.placeholder.com/150/92c952"));
    }
}
