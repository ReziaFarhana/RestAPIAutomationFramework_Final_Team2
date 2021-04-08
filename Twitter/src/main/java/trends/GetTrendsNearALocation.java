package trends;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class GetTrendsNearALocation extends RestAPI {
    private final String GET_TRENDS_PLACE_ENDPOINT = "/trends/place.json";

    public ValidatableResponse getTrends(int id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", id)
                .when().get(this.baseUrl + this.GET_TRENDS_PLACE_ENDPOINT).then();
    }


}
