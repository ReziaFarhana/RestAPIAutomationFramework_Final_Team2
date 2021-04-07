package users;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateAndManageLists extends RestAPI {

    private final String CREATE_LISTS_ENDPOINT = "/lists/create.json";
    private final String GET_LISTS_ENDPOINT = "/lists/list.json";
    private final String CREATE_MEMBERS_LISTS_ENDPOINT = "/lists/members/create.json";
    private final String GET_MEMBERS_LISTS_ENDPOINT = "/lists/members.json";
    private final String DELETE_MEMBERS_ENDPOINT = "/members/destroy.json";


    //creates a new list
    public ValidatableResponse createList(String name, String mode, String description) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("mode", mode);
        map.put("description", description);
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .params(map)
                .when().post(this.baseUrl + this.CREATE_LISTS_ENDPOINT)
                .then();
    }

    //get the list that was created
    public ValidatableResponse getList(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("screen_name", screenName)
                .when().get(this.baseUrl + this.GET_LISTS_ENDPOINT)
                .then();
    }

    //Add a member to the list
    public ValidatableResponse addAMemberToTheList(String slug, String screenName, String ownerScreenName) {
        Map<String, String> map = new HashMap<>();
        map.put("slug", slug);
        map.put("screen_name", screenName);
        map.put("owner_screen_name", ownerScreenName);

        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .params(map)
                .when().post(this.baseUrl + this.CREATE_MEMBERS_LISTS_ENDPOINT)
                .then();
    }

    public ValidatableResponse retrieveMemberFromTheList(long listID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("list_id", listID)
                .when().get(this.baseUrl + this.GET_MEMBERS_LISTS_ENDPOINT)
                .then();
    }

    public ValidatableResponse deleteMember(long listID, String memberScreenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("list_id", listID).param("screen_name", memberScreenName)
                .when().delete(this.baseUrl + this.DELETE_MEMBERS_ENDPOINT)
                .then();
    }


}
