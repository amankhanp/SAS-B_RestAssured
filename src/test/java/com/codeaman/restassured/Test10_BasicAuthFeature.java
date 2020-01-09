package com.codeaman.restassured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.get;
import static org.testng.Assert.assertEquals;

/**
 * In this class we validate rest api basic authentication.
 * for this we are using https://www.football-data.org/ api.
 * before going to test for go through the website and get
 * your api key.
 */
public class Test10_BasicAuthFeature {

    /**
     * added baseURL,basePath and set content-type to Json and passed
     * Header with basic-auth-token.
     * Note: Please use your token, which you have generated before.
     * if not created then please create your own token.
     */
    @BeforeClass
    public void testSetup(){
        RestAssured.baseURI = "http://api.football-data.org";
        RestAssured.basePath = "/v2";
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                addHeader("X-Auth-Token", "915b932bc97e4f55a26bb00b2380c080").
                build();
    }

    /**
     * Basic method which validated API status code.
     */
    @Test
    public void testAPIStatus(){
        get("teams/66").
        then().
                assertThat().
                statusCode(200).
                log().all();
    }

    @Test
    public void testAPI2(){
        Response response = get("teams/66");
        assertEquals(response.path("name"), "Manchester United FC");
    }

    @Test
    public void testAPI3(){
        Response response = get("teams/66");
        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.get("name"), "Manchester United FC");
    }

    @Test
    public void testAPI4(){
        String responseStrObj = get("/teams/66").asString();
        String name = JsonPath.from(responseStrObj).get("name");
        assertEquals(name, "Manchester United FC");
    }
}
