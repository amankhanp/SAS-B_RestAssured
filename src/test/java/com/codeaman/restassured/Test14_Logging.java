package com.codeaman.restassured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Test14_Logging {

    @Test
    public void testLogAllRequest(){
        given().
                get("https://jsonplaceholder.typicode.com/posts/3").
        then().
                log().all();
    }

    @Test
    public void testLogHeadersRequest(){
        given().
                get("https://jsonplaceholder.typicode.com/posts/3").
                then().
                log().headers();
    }

    @Test
    public void testLogBodyRequest(){
        given().
                get("https://jsonplaceholder.typicode.com/posts/3").
                then().
                log().body();
    }

    @Test
    public void testLogCookiesRequest(){
        given().
                get("https://jsonplaceholder.typicode.com/posts/3").
                then().
                log().cookies();
    }

    @Test
    public void testLogOnlyIfError(){
        given().
                get("https://jsonplaceholder.typicode.com/posts/3i").
                then().
                log().ifError();
    }

    @Test
    public void testLogOnlyIfStatusCodeIsSame(){
        given().
                get("https://jsonplaceholder.typicode.com/posts/3").
                then().
                log().ifStatusCodeIsEqualTo(200);
    }
}
