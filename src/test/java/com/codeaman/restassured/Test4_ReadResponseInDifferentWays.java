package com.codeaman.restassured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

/**
 * In this class we are reading response in different ways.s
 */
public class Test4_ReadResponseInDifferentWays {

    /**
     * In this method simply we have taken response in string format and print it on console.
     */
    @Test
    public void testGetResponseAsString(){
        String responseAsString =  get("https://jsonplaceholder.typicode.com/posts/1").asString();
        System.out.println("My Response:\n\n"+responseAsString+"\n\n -> Output is in 'String' format but not in JSON as it looks.");
    }

    /**
     * In this method simply we have taken response in InputStream format and print it on console.
     */
    @Test
    public void testGetResponseAsInputStream() throws IOException {
        InputStream responseAsInputStream =  get("https://jsonplaceholder.typicode.com/posts/1").asInputStream();
        System.out.println("My Response:\n\n"+responseAsInputStream.toString().length()+"\n\n -> Output is in 'InputStream' format but not in JSON as it looks.");
        responseAsInputStream.close();
    }

    /**
     * In this method simply we have taken response in InputStream format and print it on console.
     */
    @Test
    public void testGetResponseAsByteArray(){
        byte[] responseAsByteArray =  get("https://jsonplaceholder.typicode.com/posts/1").asByteArray();
        System.out.println("My Response:\n\n"+responseAsByteArray.length+"\n\n -> Output is in 'ByteArray' format but not in JSON as it looks.");
    }

    /**
     * To Extract details using path.
     */
    @Test
    public void testExtractDetailsUsingPath(){
        String href =
                when().
                        get("https://restcountries.eu/rest/v2/name/india?fullText=true").
                then().
                        contentType(ContentType.JSON).
                        body("name", is(Arrays.asList("India"))).
                extract().
                        path("flag").toString().replace("]","").replace("[","");
        System.out.println(href);

        when().get(href).then().statusCode(200).log().all();
    }

    /**
     * To Extract details using path in one line.
     */
    @Test
    public void testExtractDetailsUsingPathInOnePath(){
        //Method-1
        String href1 = when().get("https://restcountries.eu/rest/v2/name/india?fullText=true").path("flag").toString().replace("]","").replace("[","");
        System.out.println(href1);
        when().get(href1).then().statusCode(200).log().all();

        //Method-2
        String href2 = when().get("https://restcountries.eu/rest/v2/name/india?fullText=true").andReturn().jsonPath().getString("flag").replace("]","").replace("[","");
        System.out.println(href2);
        when().get(href2).then().statusCode(200).log().all();
    }

    /**
     * Extract details as reponse for further use.
     */
    @Test
    public void testExtractDetailsUsingResponse(){
        Response response =
                when().
                        get("https://restcountries.eu/rest/v2/name/india?fullText=true").
                then().
                        extract().response();
        System.out.println("Content Type: " +response.contentType());
        System.out.println("Path: " +response.path("flag"));
        System.out.println("Status Code: " +response.statusCode());
    }
}
