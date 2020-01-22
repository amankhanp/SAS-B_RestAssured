package com.codeaman.restassured;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * In this class, we have discussed about different types of parser
 * default, custom type of parsers and how to parse it.
 */
public class Test16_Parser {

    @Test
    public void testDefaultParser1(){
        RestAssured.defaultParser = Parser.HTML;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.defaultParser = Parser.TEXT;
        RestAssured.defaultParser = Parser.XML;
    }

    @Test
    public void testDefaultParser2(){
        given().
                get("http://thomas-bayer.com/sqlrest/CUSTOMER/10").
        then().
                defaultParser(Parser.JSON);

        given().
                get("http://thomas-bayer.com/sqlrest/CUSTOMER/10").
        then().
                defaultParser(Parser.XML);
    }

    /**
     * 'application/vnd.uoml+xml' is an custom type, will be given by developer
     */
    @Test
    public void testCustomParser1(){
        RestAssured.registerParser("application/vnd.uoml+xml", Parser.XML);
        RestAssured.unregisterParser("application/vnd.uoml+xml");
    }

    @Test
    public void testCustomParser2(){
        given().
                get("http://thomas-bayer.com/sqlrest/CUSTOMER/10").
        then().
                using().
                parser("application/vnd.uoml+xml", Parser.XML);
    }
}
