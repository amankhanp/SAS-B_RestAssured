package com.codeaman.restassured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

/**
 * In this class we are testing weather api.
 * My Unique key: a7849a4fd233d27c3c2ce2d06dbebf18
 */
public class Test11_WeatherDetails_HW {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://api.openweathermap.org/data/2.5";
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType("application/json").build();
    }

    @Test
    public void testApiStatus(){
        get("/weather?q=hyderabad&APPID=a7849a4fd233d27c3c2ce2d06dbebf18").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void testLongitudeLatitude(){
        get("/weather?lat=17.38&lon=78.47&appid=a7849a4fd233d27c3c2ce2d06dbebf18").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void testVerifyApiDetails(){
        get("/weather?q=hyderabad&APPID=a7849a4fd233d27c3c2ce2d06dbebf18").
                then().
                assertThat().
                body("name", equalTo("Hyderabad"),
                "sys.country", equalTo("IN"),
                "main.temp.toDouble()", lessThan(400.99),
                "main.temp_min.toDouble()", greaterThan(0.00));
    }
}
