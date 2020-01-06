package com.codeaman.restassured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * In this class we are simply checking post method how it works.
 */
public class Test3_PostFeature {

    @Test
    public void testPostFeature(){
        given().
                header("AppKey", "Key-Value").
                param("wtsFirst_Name", "first").
                param("wtsLast_Name", "last").
        when().
                post("http://api.fonts.com/rest/json/Accounts/").
        then().
                statusCode(401).log().all();
    }

}
