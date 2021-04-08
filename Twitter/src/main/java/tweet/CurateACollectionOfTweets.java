package tweet;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CurateACollectionOfTweets extends RestAPI {
    private final String CREATE_COLLECTIONS_ENDPOINT = "/collections/create.json";

    public ValidatableResponse createCollections(String titleName,String description, String collectionListUrl){
        Map<String,String>map = new HashMap<>();
        map.put("name",titleName);
        map.put("description",description);
        map.put("url",collectionListUrl);

        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .params(map)
                .when().post(this.baseUrl + this.CREATE_COLLECTIONS_ENDPOINT)
                .then();    }

}
