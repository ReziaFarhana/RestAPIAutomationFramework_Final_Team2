package tweet;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class FilterRealTimeTweets extends RestAPI {

    private final String POST_STATUSES_FILTER_ENDPOINT = "/statuses/filter.json";

    public ValidatableResponse statusesThatMatchTheFilter(String track){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("track", track)
                .when().get(this.baseUrl + this.POST_STATUSES_FILTER_ENDPOINT)
                .then();    }

}
