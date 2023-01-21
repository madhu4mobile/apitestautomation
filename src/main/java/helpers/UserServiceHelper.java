package helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.User;
import utils.ConfigManager;
import constants.EndPoints;

import java.lang.reflect.Type;

public class UserServiceHelper {
    private static final String BASE_URL = ConfigManager.getInstance().getString("baseUrl");

    public UserServiceHelper(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public User getUser(){
        Response response = RestAssured.given().
                log().all().
                contentType(ContentType.JSON).
                get(EndPoints.GET_USER).andReturn();

        String jsonString = response.prettyPrint();
        System.out.println(jsonString);

        Type type = new TypeReference<User>(){}.getType();
        User user = response.as(type);

        return user;
    }
}
