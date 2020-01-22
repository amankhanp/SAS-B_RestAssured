package com.codeaman.restassured;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

/**
 * In this class we validate 'response time measurement'.
 * note: time includes HTTP round trip + rest-assured processing time.
 */
public class Test13_TimeMeasurement {

    /**
     * In this method we are getting the api response time using 'time'
     * and storing in long variable and then printing the same.
     * time method gets are the response time 'milli-seconds'.
     */
    @Test
    public void testResponseTime(){
        long time = given().get("https://jsonplaceholder.typicode.com/photos/1").time();
        System.out.println("Time in milli-seconds: " +time);
    }

    /**
     * In this method we getting the api response time using 'timeIn'
     * timeIn method contains parameter which accepts TimeUnit enum
     * we can pass units in SECONDS,HOURS,MINUTESand so on.
     */
    @Test
    public void testResponseTimeInUnit(){
        long time = given().get("https://jsonplaceholder.typicode.com/photos/1").timeIn(TimeUnit.SECONDS);
        System.out.println("Time in Units: " +time);
    }

    /**
     * In this method we are just asserting the api response time
     */
    @Test
    public void testResponseTimeAssertion(){
        given().get("https://jsonplaceholder.typicode.com/photos/1").then().time(lessThan(4000L));
    }
}
