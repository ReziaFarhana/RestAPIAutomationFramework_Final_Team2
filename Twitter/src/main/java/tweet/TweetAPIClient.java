package tweet;


import base.RestAPI;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends RestAPI {

    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/home_timeline.json";
    private final String GET_USER_STATUS_SHOW="/statuses/show.json";
    private final String POST_USER_RETWEET_ENDOINT="statuses/retweet/:id.json";




    // to Get all tweet information

    public ValidatableResponse getUserTimeTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();

    }

    // Create a tweet from user Twitter
    public ValidatableResponse createTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUrl + this.CREATE_TWEET_ENDPOINT).then();
    }

    // Delete Tweet
    public ValidatableResponse DeleteTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().delete(this.baseUrl + this.DELETE_TWEET_ENDPOINT).then().statusCode(200);

    }

    // Delete a tweet from user twitter
    public ValidatableResponse deleteTweet(Long tweetId){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",tweetId)
                .when().delete(this.baseUrl+this.DELETE_TWEET_ENDPOINT).then().statusCode(200);
    }


    // Response Time check
    public ValidatableResponse responseTime(){
        System.out.println(given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .timeIn(TimeUnit.MILLISECONDS));
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .then();

    }

    // Header Value
    public void headerValue(){
        System.out.println(given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .then().extract().headers());

    }

    public  void checkProperty(){
        Response response= given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator= response.jsonPath();
        String createdAt=pathEvaluator.get("id");
        System.out.println(createdAt);
    }

    // Create a Tweet from user twitter
    public ValidatableResponse showStatus(long tweetid){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("id",tweetid)
                .when().get(this.baseUrl+this.GET_USER_STATUS_SHOW)
                .then();
    }





}
//    // OAuth
//    // https://www.programcreek.com/java-api-examples/?api=com.github.scribejava.core.model.OAuthRequest
//
//
//
//// https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/overview
//
//    // https://api.twitter.com/1.1/statuses/update.json
//


