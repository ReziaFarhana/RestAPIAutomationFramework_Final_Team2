package tweet;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.PortUnreachableException;
import java.util.SplittableRandom;

import static io.restassured.RestAssured.given;

public class BaseAPIClientTwo extends RestAPI {
    private final String GET_TWEET_ENDPOINT_2 = "/tweets";
    private final String GET_ACCOUNT_SETTING_ENDPOINT = "/account/settings.json";
    private final String POST_BLOCK_ENDPOINT = "/blocks/create.json";
    private final String GET_BLOCK_ENDPOINT = "/blocks/ids.json";
    private final String POST_UNBLOCK_ENDPOINT = "/blocks/destroy.json";
    private final String GET_COLLECTIONS_ENDPOINT = "/collections/list.json";
    private final String POST_COLLECTIONS_ENDPOINT = "/collections/create.json";
    private final String DELETE_COLLECTIONS_ENDPOINT = "/collections/destroy.json";
    private final String CREATE_FRIENDSHIPS_ENDPOINT = "/friendships/create.json";
    private final String GET_FRIENDSHIPS_ENDPOINT = "/friends/list.json";
    private final String DELETE_FRIENDSHIPS_ENDPOINT = "/friendships/destroy.json";
    private final String POST_MEMBER_CREATE_ENDPOINT = "lists/members/create.json";
    private final String POST_UPDATE_PROFILE_ENDPOINT = "/account/update_profile.json";
    private final String POST_MUTE_USERS_ENDPOINT = "/mutes/users/create.json";
    private final String POST_UNMUTE_USERS_ENDPOINT = "/mutes/users/destroy.json";
    private final String POST_MEDIA_UPLOAD_ENDPOINT = "/media/upload.json";
    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String CREATE_MESSAGES_ENDPOINT = "/direct_messages/events/new.json";

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

    public ValidatableResponse getBlockedUser() {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                when().get(baseUrl + GET_BLOCK_ENDPOINT).then();
    }

    public ValidatableResponse getTweet(String ids) {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                param("ids", ids).
                when().get(baseUrl2 + GET_TWEET_ENDPOINT_2).then();
    }

    public ValidatableResponse createCollections(String name) {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                param("name", name).
                when().post(baseUrl + POST_COLLECTIONS_ENDPOINT).then();
    }

    public ValidatableResponse getCollectionsLists(String screen_name) {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                param("screen_name", screen_name).
                when().get(baseUrl + GET_COLLECTIONS_ENDPOINT).then();
    }

    public ValidatableResponse removeCollectionsLists(String id, String user_id) {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                queryParam("id", id).queryParam("user_id", user_id).
                when().post(baseUrl + DELETE_COLLECTIONS_ENDPOINT).then();
    }

     public ValidatableResponse createFriendships(String screen_name, boolean follow) {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                queryParam("screen_name", screen_name). queryParam("follow", follow).
                when().post(baseUrl + CREATE_FRIENDSHIPS_ENDPOINT).then();
    }

    public ValidatableResponse findFollowers(){
        return  given().auth().oauth(apiKey,apiSecretKey,accessToken,accessTokenSecret).
                when().get(baseUrl+GET_FRIENDSHIPS_ENDPOINT).then();
    }

     public ValidatableResponse unfollowIndividualFollower(String screen_name){
        return  given().auth().oauth(apiKey,apiSecretKey,accessToken,accessTokenSecret).
                param("screen_name", screen_name).
                when().post(baseUrl+DELETE_FRIENDSHIPS_ENDPOINT).then();
    }

    public ValidatableResponse getAccountSetting() {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                when().get(baseUrl + GET_ACCOUNT_SETTING_ENDPOINT).then();
    }

    public ValidatableResponse createMemberList(String screen_name,long list_id){
        return  given().auth().oauth(apiKey,apiSecretKey,accessToken,accessTokenSecret).
                param("screen_name", screen_name).param("list_id", list_id).
                when().post(baseUrl+POST_MEMBER_CREATE_ENDPOINT).then();
    }

    public ValidatableResponse updateProfile(String screen_name, String location) {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                param("screen_name", screen_name).param("url", location).
                when().post(baseUrl + POST_UPDATE_PROFILE_ENDPOINT).then();
    }

    public ValidatableResponse muteUser(String screen_name) {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                param("screen_name", screen_name).
                when().post(baseUrl + POST_MUTE_USERS_ENDPOINT).then();
    }

    public ValidatableResponse unMuteUser(String screen_name) {
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                param("screen_name", screen_name).
                when().post(baseUrl + POST_UNMUTE_USERS_ENDPOINT).then();
    }

    public ValidatableResponse mediaUpload(String image){
        return given().auth().oauth(apiKey, apiSecretKey, accessToken, accessTokenSecret).
                param("media", image).
                when().post(mediaUploadUrl + POST_MEDIA_UPLOAD_ENDPOINT).then();
    }

    public ValidatableResponse tweetTheImage(String tweet, long image) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("status", tweet).
                param("media_ids", image).
                when().post(this.baseUrl + this.CREATE_TWEET_ENDPOINT).then();
    }

    public ValidatableResponse createAMessage() throws FileNotFoundException {
        FileInputStream json = new FileInputStream("../JSON_files/text_messaging.json");
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                header("content-type","application/json").
                body(json).
                when().post(this.baseUrl + this.CREATE_MESSAGES_ENDPOINT).then();
    }

    public ValidatableResponse createAMessageWithImage() throws FileNotFoundException {
        FileInputStream json = new FileInputStream("../JSON_files/text_message_with_image.json");
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                header("content-type","application/json").
                body(json).
                when().post(this.baseUrl + this.CREATE_MESSAGES_ENDPOINT).then();
    }




















}
