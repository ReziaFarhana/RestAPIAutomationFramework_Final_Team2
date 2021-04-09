package tweet;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class BaseAPIClient extends RestAPI {

    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/home_timeline.json";
    //    private final String GET_USER_TWEET_ENDPOINT = "/statuses/update.json";
    private final String GET_COL_TWEET_ENDPOINT = "/statuses/show.json";
    private final String POST_RETWEET_ENDPOINT = "/statuses/retweet.json";
    private final String POST_UNRETWEET_ENDPOINT = "/statuses/unretweet.json";
    private final String POST_FAVORITES_ENDPOINT = "/favorites/create.json";
    private final String POST_UNFAVORITES_ENDPOINT = "/favorites/destroy.json";
    private final String GET_RETWEETS_OF_ME_ENDPOINT = "/statuses/retweets_of_me.json";
    private final String GET_RERETWEET_ENDPOINT = "/statuses/retweets.json";
    private final String GET_FAVORITES_LIST_ENDPOINT = "/favorites/list.json";
    private final String GET_LISTS_LIST_ENDPOINT = "/lists/list.json";
    private final String CREATE_LISTS_LIST_ENDPOINT = "/lists/create.json";
    private final String DELETE_LISTS_LIST_ENDPOINT = "/lists/destroy.json";

    private final String GET_RERETWEETERS_ENDPOINT = "/statuses/retweeters.json";

    public ValidatableResponse getAllists(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("screen_name", screenName).
                when().get(this.baseUrl + this.GET_LISTS_LIST_ENDPOINT).then();
    }

    public ValidatableResponse userCanTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("status", tweet).
                when().post(this.baseUrl + this.CREATE_TWEET_ENDPOINT).then();
    }

    public ValidatableResponse readASingleTweetID(long ID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                queryParam("id", ID).
                when().get(this.baseUrl + this.GET_COL_TWEET_ENDPOINT).then().statusCode(200);
    }

    public ValidatableResponse deleteATweet(long ID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("id", ID).
                when().post(this.baseUrl + this.DELETE_TWEET_ENDPOINT).then();
    }

    public ValidatableResponse reTweetATweet(long ID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("id", ID).
                when().post(this.baseUrl + this.POST_RETWEET_ENDPOINT).then();
    }

    public ValidatableResponse unReTweetATweet(long ID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("id", ID).
                when().post(this.baseUrl + this.POST_UNRETWEET_ENDPOINT).then();
    }

    public ValidatableResponse favoriteATweet(long ID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("id", ID).
                when().post(this.baseUrl + this.POST_FAVORITES_ENDPOINT).then();
    }

    public ValidatableResponse unfavoriteATweet(long ID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("id", ID).
                when().post(this.baseUrl + this.POST_UNFAVORITES_ENDPOINT).then();
    }

    public ValidatableResponse listOfRetweetOfMe() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).

                when().get(this.baseUrl + this.GET_RETWEETS_OF_ME_ENDPOINT).then();
    }

    public ValidatableResponse createList(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("name", tweet).
                when().post(this.baseUrl + this.CREATE_LISTS_LIST_ENDPOINT).then();
    }

    public ValidatableResponse createListWithAccess(String tweet, String mode) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("name", tweet).param("mode", mode).
                when().post(this.baseUrl + this.CREATE_LISTS_LIST_ENDPOINT).then();
    }

    public ValidatableResponse createListWithAccessAndDescription(String tweet, String mode, String description) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("name", tweet).param("mode", mode).param("description", description).
                when().post(this.baseUrl + this.CREATE_LISTS_LIST_ENDPOINT).then();
    }

    public ValidatableResponse deleteListID(long listID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("list_id", listID).
                when().post(this.baseUrl + this.DELETE_LISTS_LIST_ENDPOINT).then();
    }

    public ValidatableResponse deleteListWithSlug(String slug, String name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                param("slug", slug).param("owner_screen_name", name).
                when().post(this.baseUrl + this.DELETE_LISTS_LIST_ENDPOINT).then();
    }

    public ValidatableResponse getFavorite() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).
                when().get(this.baseUrl + this.GET_FAVORITES_LIST_ENDPOINT).then();
    }


}
