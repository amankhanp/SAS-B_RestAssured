package com.codeaman.restassured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * In this class we are using 'spec' method for code optimization purpose.
 * we are creating a requestSpecification object which carries request related info.
 * also responseSpecification object which carries response related info.
 * these object are passed in spec method based on its uses.
 */
public class Test8_UsingSpec {

    private RequestSpecification requestSpecification = new RequestSpecBuilder().
            setBaseUri("https://petstore.swagger.io/v2").
            build();

    private ResponseSpecification responseSpecification = new ResponseSpecBuilder().
            expectStatusCode(HttpStatus.SC_OK).
            expectContentType(ContentType.JSON).
            build();


    @Test
    public void testAPI(){
        given().
                spec(requestSpecification).
        when().
                get("/pet/3").
        then().
                assertThat().
                spec(responseSpecification).
                log().all();
    }

    @Test
    public void testPetDetails(){
        given().
                spec(requestSpecification).
        when().
                get("pet/3").
        then().
                assertThat().spec(responseSpecification).
                body("id", equalTo(3)).
                body("category.name", equalTo("rich")).
                body("name", equalTo("doggie"));
    }

    /**
     * In this method we have used body method to compare different inputs from response
     * Note: It act as a 'Hard-Assertion': which means if one body method validation failed
     * then it won't care about other method and simply break the execution and throws an
     * error saying Json path name doesn't match.
     * For better understand this method is used for negative case validation.
     */
    @Test
    public void testUsingSpecWithBodyMethod1(){
        given().
                spec(requestSpecification).
        when().
                get("pet/3").
        then().
                assertThat().spec(responseSpecification).
                body("id", equalTo(3)).
                body("category.name", equalTo("rich")).
                body("name", equalTo("doggiie")).
                body("tags[0].id", equalTo(0)).
                body("tags[0].name", equalTo("string")).
                body("status",equalTo("availaable"));
    }

    /**
     * In this method we have used body method to compare different inputs from response
     * Note: It act as a 'Soft-Assertion': which means if one validation fails its simply
     * note downs and goes to next validation part like wise till the end.
     * For better understand this method is used for negative case validation.
     */
    @Test
    public void testUsingSpecWithBodyMethod2(){
        given().
                spec(requestSpecification).
        when().
                get("pet/3").
        then().
                assertThat().spec(responseSpecification).
                body("id", equalTo(3),
                        "category.name", equalTo("rich"),
                        "name", equalTo("doggiie"),
                        "tags[0].id", equalTo(0),
                        "tags[0].name", equalTo("string"),
                        "status",equalTo("availaable"));
    }
}
