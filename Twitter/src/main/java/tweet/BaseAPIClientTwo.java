package tweet;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class BaseAPIClientTwo extends RestAPI {
    private final String POST_BLOCK_ENDPOINT = "/blocks/create.json";
    private final String GET_BLOCK_ENDPOINT = "/blocks/ids.json";
    private final String POST_UNBLOCK_ENDPOINT = "/blocks/destroy.json";

    public ValidatableResponse createABlockAUser(String screen_name) {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                param("screen_name", screen_name).
                when().post(baseUrl + POST_BLOCK_ENDPOINT).then();
    }

     public ValidatableResponse unBlockABloackedUser(String screen_name) {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                param("screen_name", screen_name).
                when().post(baseUrl + POST_UNBLOCK_ENDPOINT).then();
    }




    public ValidatableResponse getBlockedUser(){
        return given().auth().oauth(apiKey,apiSecretKey,accessToken,accessTokenSecret).
                when().get(baseUrl+GET_BLOCK_ENDPOINT).then();
    }

    public ValidatableResponse getBlockedUserWithID(long ids) {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                param("stringify_ids", ids).
                when().get(baseUrl + GET_BLOCK_ENDPOINT).then();
    }




    public ValidatableResponse createListWissAndDescription(String... strings) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("name", strings).param("mode", strings).param("description", strings).
                when().post(this.baseUrl + this.POST_BLOCK_ENDPOINT).then();
    }
}
