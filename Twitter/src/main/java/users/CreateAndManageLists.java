package users;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateAndManageLists extends RestAPI {

    private final String CREATE_LISTS_ENDPOINT = "/lists/create.json";

    //creates a new list
    public ValidatableResponse createList(String name, String mode, String description) {
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("mode",mode);
        map.put("description",description);
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .params(map)
                .when().post(this.baseUrl + this.CREATE_LISTS_ENDPOINT)
                .then();
    }



}
