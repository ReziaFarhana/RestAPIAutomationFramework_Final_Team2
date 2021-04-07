package users;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class MuteBlockAndReportUsers extends RestAPI {
    private final String CREATE_BLOCKS_ENDPOINT = "/blocks/create.json";
    private final String GET_BLOCKS_LIST_ENDPOINT = "/blocks/list.json";
    private final String UNBLOCK_USER_ENDPOINT = "/blocks/destroy.json";


    //Blocks a user
    public ValidatableResponse blockAUser(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("screen_name",screenName)
                .when().post(this.baseUrl + this.CREATE_BLOCKS_ENDPOINT)
                .then();
    }

    //retrieve thr list of blocked user
    public ValidatableResponse retrieveBlockedUsers(boolean skipStatus) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("skip_status",skipStatus)
                .when().get(this.baseUrl + this.GET_BLOCKS_LIST_ENDPOINT)
                .then();
    }

    //Unblock a user
    public ValidatableResponse unblockABlockedUsers(String screenName, boolean skipStatus) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("screen_name",screenName).param("skip_status",skipStatus)
                .when().post(this.baseUrl + this.UNBLOCK_USER_ENDPOINT)
                .then();
    }




}
