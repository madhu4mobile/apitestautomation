package apiTests;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class E2E_reqres_user_2 {

    public static void main(String[] args) {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");


        //Step - 1
        //base URI with Rest Assured class
        String baseUrl = "https://reqres.in";
        RestAssured.baseURI = baseUrl;



        //input details and Headers
        //RestAssured.proxy = host("xxxorgcproxy.yy.org.com").withPort(8443);
        RequestSpecification h = RestAssured.given();

        //Test-1 with path single user-2
        Response response = request.get("/api/users/2");

        //Response body
        ResponseBody body = response.getBody();
        //convert response body to string
        String b = body.asString();
        //JSON Representation from Response Body
        JsonPath myJson = response.jsonPath();
        //Get value of Location Key
        String id = myJson.get("data.id");
        System.out.println("user id : " + id);
        String email = myJson.get("data.email");
        System.out.println("user email: " + email);
        String first_name = myJson.get("data.first_name");
        System.out.println("User First Name: " + first_name);
        String last_name = myJson.get("data.last_name");
        System.out.println("User Last Name: " + last_name);

        Assert.assertEquals(response.getStatusCode(), 200);

        String jsonString = response.asString();
        //List<Map<String, String>> data = JsonPath.from(jsonString).get("data");
/*        String first_name = JsonPath.from(jsonString).get("first_name");
        String last_name = JsonPath.from(jsonString).get("last_name");

        System.out.println("Name obtained from the response :"+first_name+" "+last_name);
        System.out.println("jsonString :"+jsonString);
        System.out.println("Name obtained from the response :"+first_name+" "+last_name);*/

        //This bookId will be used in later requests, to add the book with respective isbn
        String body2 = response.getBody().asPrettyString();
        System.out.println("The response body :"+body2);
        Json myResponse = response.jsonPath().get();
        System.out.println(myResponse);
        //Assert.assertTrue(email.size() > 0);


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