package apiTests;

import java.util.List;
import java.util.Map;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class E2E_reqres {

    public static void main(String[] args) {
        String baseUrl = "https://reqres.in";

        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();


        //Step - 1
        //Test will start from generating Token for Authorization
        //request.header("Content-Type", "application/json");


        //Step - 2
        // Get Books - No Auth is required for this.
        Response response = request.get("api/users/2");

        Assert.assertEquals(response.getStatusCode(), 200);

//      Traverse through the response
        String jsonString = response.prettyPrint();

        System.out.println("response body :"+jsonString);
        JsonPath myJson = response.jsonPath();
        int userId = myJson.get("data.id");
        System.out.println("userId :"+userId);
        String email = myJson.get("data.email");
        System.out.println("user email :"+email);

        String supportUrl = myJson.get("support.url");
        System.out.println("Support url :"+supportUrl);


/*
        //Step - 3
        // Add a book - with Auth
        //The token we had saved in the variable before from response in Step 1,
        //we will be passing in the headers for each of the succeeding request
        request.header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

        response = request.body("{ \"userId\": \"" + userID + "\", " +
                        "\"collectionOfIsbns\": [ { \"isbn\": \"" + bookId + "\" } ]}")
                .post("/BookStore/v1/Books");

        jsonString = response.asString();
        List<Map<String, String>> collectionOfIsbns = JsonPath.from(jsonString).get("username");

        String firstIsbn = collectionOfIsbns.get(0).get("username");
        System.out.println("firstIsnb"+firstIsbn);

        Assert.assertEquals( 201, response.getStatusCode());



        //Step - 4
        // Delete a book - with Auth
        request.header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

        response = request.body("{ \"isbn\": \"" + bookId + "\", \"userId\": \"" + userID + "\"}")
                .delete("/BookStore/v1/Book");

        Assert.assertEquals(204, response.getStatusCode());

        //Step - 5
        // Get User
        request.header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

        response = request.get("/Account/v1/User/" + userID);
        Assert.assertEquals(200, response.getStatusCode());

        jsonString = response.asString();
        List<Map<String, String>> booksOfUser = JsonPath.from(jsonString).get("books");
        Assert.assertEquals(0, booksOfUser.size());

        */
    }
}