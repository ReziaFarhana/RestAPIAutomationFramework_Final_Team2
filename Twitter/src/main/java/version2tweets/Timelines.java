package version2tweets;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class Timelines extends RestAPI {
    private final String apiVersion2="2";
    private final String GET_USERS_MENTIONS = "/users/:id/mentions";

public ValidatableResponse usersMention(String tweetID){
    return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
            .param("id",tweetID)
            .when().get(this.baseUrl + this.GET_USERS_MENTIONS)
            .then();
}


}
