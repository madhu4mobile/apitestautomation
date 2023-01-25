


//import dataentities.Address;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.*;
import io.restassured.http.*;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.*;
import org.eclipse.jetty.client.Origin;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredDemoTest {

/*    @Test
    public void usePreviouslyStoredAuthToken() {

        given().
                auth().
                oauth2("myAuthenticationToken").
                when().
                get("https://my.very.secure/api").
                then().
                assertThat().
                statusCode(200);
    }*/

    @Test
    public void getUserData_verifyName_shouldBeLeanneGraham() {

        given().
                when().
                get("http://jsonplaceholder.typicode.com/users/1").  // Do a GET call to the specified resource
                then().
                assertThat().                                           // Assert that the value of the element 'name'
                body("name", equalTo("Leanne Graham"));       // in the response body equals 'Leanne Graham'
    }

    @Test
    public void logAllRequestData() {

        given().
                log().all().
                when().
                get("http://jsonplaceholder.typicode.com/users/1").
                then().
                assertThat().
                body("name", equalTo("Leanne Graham"));
    }

    @Test
    public void logAllResponseData() {

        given().
                when().
                get("http://jsonplaceholder.typicode.com/users/1").
                then().
                log().all().
                and().
                assertThat().
                body("name", equalTo("Leanne Graham"));
    }


    @Test
    public void getUserData_verifyStatusCodeAndContentType() {

        given().
                when().
                get("http://jsonplaceholder.typicode.com/users/1").
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON);
    }

    @Test
    public void useQueryParameter() {

        given().
                queryParam("text", "testcase").
                when().
                get("http://md5.jsontest.com").
                then().
                assertThat().
                body("md5", equalTo("7489a25fc99976f06fecb807991c61cf"));
    }

    @Test
    public void usePathParameter() {

        given().
                pathParam("userId",1).
                when().
                get("http://jsonplaceholder.typicode.com/users/{userId}").
                then().
                assertThat().
                body("name", equalTo("Leanne Graham"));
    }

    @ParameterizedTest
    @CsvSource({
            "1, Leanne Graham",
            "2, Ervin Howell",
            "3, Clementine Bauch"
    })
    public void checkNameForUser
            (int userId, String expectedUserName) {

        given().
                pathParam("userId", userId).
                when().
                get("http://jsonplaceholder.typicode.com/users/{userId}").
                then().
                assertThat().
                body("name",equalTo(expectedUserName));
    }

    @Test
    public void useBasicAuthentication() {

        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
        auth.setUserName("my username");
        auth.setPassword("my password");

        // was getting desperate so tried adding this
        System.setProperty("http.proxyHost", "");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("http.proxyUser", "test_rest");
        System.setProperty("http.proxyPassword", "rest@123");
        System.setProperty("https.proxyHost", "");
        System.setProperty("https.proxyPort", "8080");
        System.setProperty("https.proxyUser", "test_rest");
        System.setProperty("https.proxyPassword", "rest@123");



        given().
                auth().
                preemptive().
                basic("username", "password").
                when().
                get("https://my.secure/api").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void useOAuthAuthentication() {

        given().
                auth().
                oauth2("myAuthenticationToken").
                when().
                get("https://my.very.secure/api").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void captureAndReuseUserId() {

        int userId =

                given().
                        contentType(ContentType.JSON).
                        when().
                        get("https://reqres.in/api/users").
                        then().
                        extract().
                        response().
                        body().
                        path("data[4].id");
/*                        when().
                        post("https://reqres.in/api/users").
                        then().
                        extract().response().body().
                        path("data.id");*/

        given().
                pathParam("userId", userId).
                when().
                get("https://reqres.in/api/users/{userId}").
                then().
                assertThat().
                statusCode(200);
    }

    private ResponseSpecification responseSpec;

    @BeforeEach
    public void createResponseSpec() {

        responseSpec =
                new ResponseSpecBuilder().
                        expectStatusCode(200).
                        expectContentType(ContentType.JSON).
                        build();
    }

    @Test
    public void useResponseSpec() {

        given().
                when().
                get("http://jsonplaceholder.typicode.com/users/1").
                then().
                spec(responseSpec).
                and().
                body("name", equalTo("Leanne Graham"));
    }

    private RequestSpecification requestSpec;

/*    @BeforeEach
    public void createRequestSpec() {

        requestSpec =
                new RequestSpecBuilder().
                        setBaseUri("http://api.zippopotam.us").
                        //setPort(8443).
                        build();
    }

    @Test
    public void useRequestSpec() {

        given().
                spec(requestSpec).
                when().
                get("/us/90210.json").
                then().
                assertThat().
                statusCode(200);
    }*/

    @Test
    public void deserializeJsonToZip() {

        RestAssured.baseURI ="https://demoqa.com";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("UserName", "test_rest");
        requestParams.put("Password", "rest@123");
        request.body(requestParams.toString());
        Response response = request.post("/Account/v1/User");
        ResponseBody body = response.getBody();
        System.out.println(response.getBody().asString());
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());
        System.out.println(body.asPrettyString());
//// Deserialize the Response body into JSONSuccessResponse
//        JSONSuccessResponse responseBody = body.as(JSONSuccessResponse.class);
//// Use the JSONSuccessResponseclass instance to Assert the values of Response.
//        Assert.assertEquals("OPERATION_SUCCESS", responseBody.SuccessCode);
//        Assert.assertEquals("Operation completed successfully", responseBody.Message);



/*        Origin.Address myAddress =

                given().
                        when().
                        get("http://localhost:9876/address").
                        as(Origin.Address.class);

        assertEquals("Amsterdam", "'myAddress.getCity()'");*/
        /*
        Response myJson = given().when().get("https://reqres.in/api/users/2");
        User user = objectMapper.readValue(myJson, data.first_name);
        */
    }
}
