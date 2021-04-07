package tweet;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class GetTweetTimelines extends RestAPI {
    private final String GET_HOME_TIMELINE_ENDPOINT = "/statuses/home_timeline.json";
    private final String GET_MENTIONS_TIMELINE_ENDPOINT = "/statuses/mentions_timeline.json";
    private final String GET_USERS_TIMELINE_ENDPOINT = "/statuses/user_timeline.json";




    //Collection of most recent tweets and retweets
    public ValidatableResponse retrieveTweetsNReTweetsFromHomeTimeline(String name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("name", name)
                .when().get(this.baseUrl + this.GET_HOME_TIMELINE_ENDPOINT)
                .then();
    }

//retrieve recent mentions
public ValidatableResponse retrieveMentionsFromTimeline(String name) {
    return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
            .param("name", name)
            .when().get(this.baseUrl + this.GET_MENTIONS_TIMELINE_ENDPOINT)
            .then();
}

//    //retrieve user timeline
//    public ValidatableResponse retrieveRecentTweetFromUserTimeline(Long userID) {
//        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
//                .param("id", userID)
//                .when().get(this.baseUrl + this.GET_USERS_TIMELINE_ENDPOINT)
//                .then();
//    }
        //retrieve user timeline
    public ValidatableResponse retrieveRecentTweetFromUserTimeline(String name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("name", name)
                .when().get(this.baseUrl + this.GET_USERS_TIMELINE_ENDPOINT)
                .then();
    }
}
