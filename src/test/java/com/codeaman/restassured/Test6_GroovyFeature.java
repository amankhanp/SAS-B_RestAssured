package com.codeaman.restassured;

import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class Test6_GroovyFeature {

    /**
     * In this method we validate API response size. Using Groovy method 'size()',
     * which counts Objects.
     */
    @Test
    public void testValidateApiResponseSize(){
        given().
                baseUri("https://jsonplaceholder.typicode.com").
        when().
                get("/users").
        then().
                body("size()", greaterThan(9));
    }

    /**
     * In this method we are just validate API response contains given string.
     * Using key.any{'value'} we just pass a value which compares the all objects key values.
     */
    @Test
    public void testValidateApiResponseContainsGivenString(){
        given().
                baseUri("https://jsonplaceholder.typicode.com").
        when().
                get("/users").
        then().
                body("name.any{'Ervin Howell'}", is(true));
    }

    /**
     * In this method, we are using RestAssured implemented in Groovy and hence Groovy advantages can be taken
     * Here we're adding length of all "name" code coming in response.
     */
    @Test
    public void testValidateLengthOfResponseUsingGroovy() {
        given().get("https://restcountries.eu/rest/v2/").
                then().body("name*.length().sum()", greaterThan(1000));
    }

    /**
     * In this method we want to validate our choose country is present in the response or not by 'Java',
     * we are taken the response as string and stored in a string variable
     * then from response using 'key' fetched values and those are stored in a list and print the same in console.
     * Using for loop validating whether our choose country is present or not.
     */
    @Test
    public void testGetResponseAsList() {
        String response = get("https://restcountries.eu/rest/v2/").asString();

        List<String> allCountryNamesList = from(response).getList("name");

        System.out.println("ListSize: " + allCountryNamesList.size());

        for (String countryName : allCountryNamesList) {
            if (countryName.equals("India")) {
                System.out.println("Matched my request with : " + countryName);
            }
        }
    }

    /**
     * In this method we want to validate our choose country is present in the response or not by 'Groovy',
     * In the above program it took 4 lines to validate our matching string. but in groovy we can do by one line.
     */
    @Test
    public void testConditionOnList(){
        String response = get("https://restcountries.eu/rest/v2/").asString();
        List<String> indiaCountryNamedList = from(response).getList("findAll{it.name.contentEquals(\"India\")}.name");
        System.out.println("Matched my request with : "+indiaCountryNamedList);
    }

}
