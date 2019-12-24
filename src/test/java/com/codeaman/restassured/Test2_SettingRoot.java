package com.codeaman.restassured;

import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * In this class we will learn about root settings.
 */
public class Test2_SettingRoot {

    /**
     * URL: https://restcountries.eu/rest/v2/name/india?fullText=true
     * Output for above url: [{"name":"India","topLevelDomain":[".in"],"alpha2Code":"IN","alpha3Code":"IND","callingCodes":["91"],"capital":"New Delhi","altSpellings":["IN","Bhārat","Republic of India","Bharat Ganrajya"],"region":"Asia","subregion":"Southern Asia","population":1295210000,"latlng":[20.0,77.0],"demonym":"Indian","area":3287590.0,"gini":33.4,"timezones":["UTC+05:30"],"borders":["AFG","BGD","BTN","MMR","CHN","NPL","PAK","LKA"],"nativeName":"भारत","numericCode":"356","currencies":[{"code":"INR","name":"Indian rupee","symbol":"₹"}],"languages":[{"iso639_1":"hi","iso639_2":"hin","name":"Hindi","nativeName":"हिन्दी"},{"iso639_1":"en","iso639_2":"eng","name":"English","nativeName":"English"}],"translations":{"de":"Indien","es":"India","fr":"Inde","ja":"インド","it":"India","br":"Índia","pt":"Índia","nl":"India","hr":"Indija","fa":"هند"},"flag":"https://restcountries.eu/data/ind.svg","regionalBlocs":[{"acronym":"SAARC","name":"South Asian Association for Regional Cooperation","otherAcronyms":[],"otherNames":[]}],"cioc":"IND"}]
     * output is from array-list.
     */
    @Test
    public void testWithArrayList(){
        given().
                get("https://restcountries.eu/rest/v2/name/india?fullText=true").
        then().
                body("name", is(Arrays.asList("India"))).
                body("topLevelDomain", is(Arrays.asList(Arrays.asList(".in")))).
                log().all();
    }

    /**
     * Simply validating the parameters without using root method.
     * ParentPath is 'translations' or 'regionalBlocs' and ChildPath is rest given paths like de,es,pt,acronym and so on.
     */
    @Test
    public void testWithoutRoot(){
        given().
                get("https://restcountries.eu/rest/v2/name/india?fullText=true").
        then().
                body("name", is(Arrays.asList("India"))).
                body("translations.de", is(Arrays.asList("Indien"))).
                body("translations.es", is(Arrays.asList("India"))).
                body("translations.pt", is(Arrays.asList("Índia"))).
                body("translations.fa", is(Arrays.asList("هند"))).
                body("translations.ja", is(Arrays.asList("インド"))).
                body("regionalBlocs.acronym", is(Arrays.asList(Arrays.asList("SAARC")))).
                log().all();
    }

    /**
     * Simply validating the parameters with using root method.
     * now we can simply add the root directory in root method which is a recommended way to maintain good practice.
     * In latest version root() is @deprecated, as we know use of deprecated code will leads to bad code.
     * so, Instead of root() we are using rootPath().
     */
    @Test
    public void testWitRoot(){
        given().
                get("https://restcountries.eu/rest/v2/name/india?fullText=true").
        then().
               body("name", is(Arrays.asList("India"))).
               rootPath("translations").
               body("de", is(Arrays.asList("Indien"))).
               body("es", is(Arrays.asList("India"))).
               body("pt", is(Arrays.asList("Índia"))).
               log().all();
    }

    /**
     * Simply validating the parameters with using root & detachRoot method.
     * now we can simply add the root directory in root method which is a recommended way to maintain good practice,
     * also we can detach the root directory whenever and wherever we need.
     * In latest version root() & detachRoot() is @deprecated, as we know use of deprecated code will leads to bad code.
     * so, Instead of root() & detachRoot() we are using rootPath() & detachRootPath().
     */
    @Test
    public void testDetachRoot(){
        given().
                get("https://restcountries.eu/rest/v2/name/india?fullText=true").
        then().
                body("name", is(Arrays.asList("India"))).
                rootPath("translations").
                body("de", is(Arrays.asList("Indien"))).
                body("es", is(Arrays.asList("India"))).
                body("pt", is(Arrays.asList("Índia"))).
                detachRootPath("translations").
                body("translations.fr", is(Arrays.asList("Inde"))).
                body("regionalBlocs.acronym", is(Arrays.asList(Arrays.asList("SAARC")))).
                log().all();
    }

}
