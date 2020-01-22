package com.codeaman.restassured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

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
}
