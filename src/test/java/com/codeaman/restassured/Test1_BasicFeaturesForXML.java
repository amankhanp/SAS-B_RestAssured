package com.codeaman.restassured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * In this class we are simply checking status code using Rest Assured for XML.
 */
public class Test1_BasicFeaturesForXML {

    /**
     * Simply we are hitting given XML api ("http://thomas-bayer.com/sqlrest/CUSTOMER/10").
     * and then validating single content response and printing all its response. That's all with this method.
     */
    @Test
    public void testStatusCode(){
        given().get("http://thomas-bayer.com/sqlrest/CUSTOMER/10").
        then().body("CUSTOMER.CITY", equalTo("Dallas")).log().all();
    }

    /**
     * Simply we are hitting given XML api ("http://thomas-bayer.com/sqlrest/CUSTOMER/10").
     * and then validating multiple content response and printing all its response.
     */
    @Test
    public void testMultipleContent(){
        given().get("http://thomas-bayer.com/sqlrest/CUSTOMER/10").
        then().
            body("CUSTOMER.CITY", equalTo("Dallas")).
            body("CUSTOMER.ID", equalTo("10")).
            body("CUSTOMER.FIRSTNAME", equalTo("Sue")).
            log().all();
    }

    /**
     * Simply we are hitting given XML api ("http://thomas-bayer.com/sqlrest/CUSTOMER/10").
     * then compare complete text in one go.
     * This is not a good way to validate complete response in one go.
     * it is just to understand we can do this too.
     */
    @Test
    public void testCompleteTextInOneGo(){
        given().get("http://thomas-bayer.com/sqlrest/CUSTOMER/10").
        then().body("CUSTOMER.text()", equalTo("10SueFuller135 Upland Pl.Dallas"));
    }

    /**
     * Simply we are hitting given XML api ("http://thomas-bayer.com/sqlrest/CUSTOMER/10").
     * then xpaths can also be used to find the values and also used containsString method/function.
     */
    @Test
    public void testUsingXpath1(){
        given().get("http://thomas-bayer.com/sqlrest/CUSTOMER/10").
        then().body(hasXPath("/CUSTOMER/FIRSTNAME", containsString("Sue")));
    }

    /**
     * Simply we are hitting given XML api ("http://thomas-bayer.com/sqlrest/CUSTOMER").
     * With xpath 'CUSTOMERList/CUSTOMER' we are checking some text which is '20' contains or not.
     */
    @Test
    public void testUsingXpath2(){
        given().get("http://thomas-bayer.com/sqlrest/CUSTOMER").
        then().body(hasXPath("/CUSTOMERList/CUSTOMER[text()='20']")).log().all();
    }

}
