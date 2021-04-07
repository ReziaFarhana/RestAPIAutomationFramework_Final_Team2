package users;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class FollowSearchAndGetUsers extends RestAPI {
    private final String GET_FOLLOWERS_LIST_ENDPOINT = "/followers/list.json";

    //returns a list of user's followers
    public ValidatableResponse getFollowersList(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("screen_name",screenName)
                .when().get(this.baseUrl + this.GET_FOLLOWERS_LIST_ENDPOINT)
                .then();
    }
}
