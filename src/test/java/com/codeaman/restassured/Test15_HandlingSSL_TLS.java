package com.codeaman.restassured;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * suppose server has invalid certificate and throwing
 * 'SSLPeerUnverifiedException' exception. so to handle
 * this case we can relax certificate condition and now
 * SSL exception will not come.
 *
 * note: below used url is not proper example to validate.
 */
public class Test15_HandlingSSL_TLS {

    /**
     * In this method, 'useRelaxedHTTPSValidation' is used.
     * by default it is 'SSL'.
     * In given below examples to relax the certification part.
     */
    @BeforeClass
    public void setUp(){
        RestAssured.useRelaxedHTTPSValidation();
    }

    /**
     * In beforeClass method we are already used
     * relax certification method.
     */
    @Test
    public void testSSL(){
        given().
                get("https://www.codeaman.com/").
        then().
                statusCode(200);
    }

    /**
     * This is an another way to relax certificate part
     * using 'relaxedHTTPSValidation' method we can achieve
     * that and don't in beforeClass method separately.
     */
    @Test
    public void test2SSL(){
        given().
                relaxedHTTPSValidation().
        when().
                get("https://www.codeaman.com/").
        then().
                statusCode(200);
    }

    /**
     * we can also handle TLS protocol using an overload method.
     */
    @Test
    public void testTLS(){
        given().
                relaxedHTTPSValidation("TLS").
        when().
                get("https://www.codeaman.com/").
        then().
                statusCode(200);
    }
}
