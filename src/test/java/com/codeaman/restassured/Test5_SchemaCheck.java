package com.codeaman.restassured;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;

/**
 * In this class we will learn about Schema Validation.
 */
public class Test5_SchemaCheck {

    /**
     * This test will verify the response schema with predefined existing schema.
     * What ever the response come with any value we don't care about values,
     * We just validate its schema is correct or not.
     * Path: test_json_to_jsonSchema.json
     */
    @Test
    public void testSchema(){
        given().get("https://jsonplaceholder.typicode.com/comments?id=1").
        then().assertThat().body(matchesJsonSchemaInClasspath("test_json_to_jsonSchema.json"));
    }
    /*
    For negative validation, change schema value and validate the response.

    OUTPUT: java.lang.AssertionError: 1 expectation failed.
    Response body doesn't match expectation.
    Expected: The content to match the given JSON schema.
    error: instance type (string) does not match any allowed primitive type (allowed: ["integer"])
    level: "error"
    schema: {"loadingURI":"file:/C:/Users/Amanullah_Pathan/IdeaProjects/SAS-B_RestAssured/target/test-classes/test_json_to_jsonSchema.json#","pointer":"/items/0/properties/body"}
    instance: {"pointer":"/0/body"}
    domain: "validation"
    keyword: "type"
    found: "string"
    expected: ["integer"]

    Actual: [
    {
     "postId": 1,
      "id": 1,
     "name": "id labore ex et quam laborum",
     "email": "Eliseo@gardner.biz",
     "body": "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
     }
    ]
     */
}
