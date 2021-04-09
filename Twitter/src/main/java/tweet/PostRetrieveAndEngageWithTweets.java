package tweet;

import base.RestAPI;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class PostRetrieveAndEngageWithTweets extends RestAPI {

    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/home_timeline.json";
    private final String CREATE_RETWEET_ENDPOINT = "/statuses/retweet.json";
    private final String POST_FAVORITES_ENDPOINT = "/favorites/create.json";
    private final String POST_UNFAVORITE_ENDPOINT = "/favorites/destroy.json";
    private final String GET_FAVORITE_LIST_ENDPOINT = "/favorites/list.json";
    private final String CREATE_UNRETWEET_ENDPOINT = "/statuses/unretweet.json";
    private final String GET_STATUS_ENDPOINT = "/statuses/show.json";
    private final String GET_STATUS_LOOKUP_ENDPOINT = "/statuses/lookup.json";
    private final String GET_STATUS_RETWEET_ENDPOINT = "/statuses/retweets.json";
    private final String GET_STATUS_RETWEETS_OF_ME_ENDPOINT = "/statuses/retweets_of_me.json";
    private final String GET_STATUS_RETWEETERS_ENDPOINT = "/statuses/retweeters/ids.json";
    private final String GET_SEARCH_TWEETS_ENDPOINT = "/search/tweets.json";


    // GET all Tweet Information
    public ValidatableResponse getUserTimeTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT).then().statusCode(200);
    }

    // GET all Tweet Information
    public Response getUserTimeTweet1() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT).thenReturn();
    }

    // Create a Tweet from user twitter
    public ValidatableResponse createTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUrl + this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    // Delete a tweet from user twitter
    public ValidatableResponse deleteTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.DELETE_TWEET_ENDPOINT).then().statusCode(200);
    }

    //Retweet a old tweet
    public ValidatableResponse reTweet(Long tweetID, String name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetID).param("name", name)
                .when().post(this.baseUrl + this.CREATE_RETWEET_ENDPOINT)
                .then();
    }

    //Favorite another user's tweet
    public ValidatableResponse favoriteTweet(Long tweetID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetID)
                .when().post(this.baseUrl + this.POST_FAVORITES_ENDPOINT)
                .then();
    }

    //Unfavorite another user's tweet
    public ValidatableResponse unfavoriteTweet(Long tweetID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetID)
                .when().post(this.baseUrl + this.POST_UNFAVORITE_ENDPOINT)
                .then();
    }

    //Unretweeting a reTweet
    public ValidatableResponse unReTweet(Long tweetID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetID)
                .when().post(this.baseUrl + this.CREATE_UNRETWEET_ENDPOINT)
                .then();
    }

    //Get list of favorites
    public Response favoritesList(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("name", screenName).when().get(this.baseUrl+this.GET_FAVORITE_LIST_ENDPOINT).thenReturn();
    }

//Show single tweet
    public ValidatableResponse showSingleTweet(Long tweetID){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetID)
                .when().get(this.baseUrl + this.GET_STATUS_ENDPOINT)
                .then();
    }

    //Multiple tweets lookup
//    public ValidatableResponse showTweetsInBulk( Long id,Long tweetID){
//        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
//        .param("id",id) .param("id", tweetID)
//                .when().get(this.baseUrl + this.GET_STATUS_LOOKUP_ENDPOINT)
//                .then();
//    }

    //Multiple tweets lookup
    public Response showTweetsInBulk(String name, List<Long> tweetID){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("name",name).param("id",tweetID)
                .when().get(this.baseUrl + this.GET_STATUS_LOOKUP_ENDPOINT)
                .thenReturn();
    }

    //Collection of most recent retweets
    public Response collectionOfRecentReTweets(Long tweetID){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id",tweetID)
                .when().get(this.baseUrl + this.GET_STATUS_RETWEET_ENDPOINT).thenReturn();
    }

    //Recent tweets that have been retweeted by others
    public Response reTweetsOfMe(String name){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("name",name)
                .when().get(this.baseUrl + this.GET_STATUS_RETWEETS_OF_ME_ENDPOINT).thenReturn();
    }

    //Get user IDs belonging to user who retweeted the tweet
    public ValidatableResponse retrieveReTweeters(Long tweetID){
       return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id",tweetID)
                .when().get(this.baseUrl + this.GET_STATUS_RETWEETERS_ENDPOINT).then();
    }

    public ValidatableResponse retrieveReleventTweets(String q, String resultType){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q",q).param("result_type",resultType)
                .when().get(this.baseUrl + this.GET_SEARCH_TWEETS_ENDPOINT).then();
    }

    // Response Time check
    public ValidatableResponse responseTime() {
        System.out.println(given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .timeIn(TimeUnit.MILLISECONDS));
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();

    }

    // Header Value
    public void headerValue() {
        System.out.println(given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then().extract().headers());
    }

    public void checkProperty() {
        Response response = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator = response.jsonPath();
        String createdAt = pathEvaluator.get("id");
        System.out.println(createdAt);
    }


}
