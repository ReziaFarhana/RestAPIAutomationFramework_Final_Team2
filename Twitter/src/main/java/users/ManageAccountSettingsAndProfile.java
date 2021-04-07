package users;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class ManageAccountSettingsAndProfile extends RestAPI {
    private final String GET_ACCOUNT_SETTINGS_ENDPOINT = "/account/settings.json";
    private final String GET_ACCOUNT_VERIFY_CREDENTIALS_ENDPOINT = "/account/verify_credentials.json";
    private final String GET_USERS_PROFILE_BANNER_ENDPOINT = "/users/profile_banner.json";
    private final String POST_ACCOUNT_REMOVE_PROFILE_BANNER_ENDPOINT = "/account/remove_profile_banner.json";
    private final String POST_ACCOUNT_UPDATE_PROFILE_ENDPOINT = "/account/update_profile.json";
    private final String GET_SAVED_SEARCHES_LIST_ENDPOINT = "/saved_searches/list.json";
    private final String GET_SAVED_SEARCHES_SHOW_ENDPOINT = "/saved_searches/show.json";
    private final String CREATE_SAVED_SEARCHES_ENDPOINT = "/saved_searches/create.json";
    private final String DELETE_SAVED_SEARCH_ENDPOINT = "/saved_searches/destroy.json";

    //returns user settings
    public ValidatableResponse returnUserSettings() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_ACCOUNT_SETTINGS_ENDPOINT)
                .then();
    }

    //Check whether user credential is valid
    public ValidatableResponse verifyCredential(String name, boolean includeEmail) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("name", name).param("include_email", includeEmail)
                .when().get(this.baseUrl + this.GET_ACCOUNT_VERIFY_CREDENTIALS_ENDPOINT)
                .then();
    }

    //Check user's profile banner
    public ValidatableResponse verifyProfileBanner(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("screen_name", screenName)
                .when().get(this.baseUrl + this.GET_USERS_PROFILE_BANNER_ENDPOINT)
                .then();
    }

    //Removes the uploaded profile banner
    public ValidatableResponse removeProfileBanner() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().post(this.baseUrl + this.POST_ACCOUNT_REMOVE_PROFILE_BANNER_ENDPOINT)
                .then();
    }

    //updates the language, and time zone
    public ValidatableResponse updatesUserSettings(String language, String timeZone) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("lang", language).param("time_zone", timeZone)
                .when().post(this.baseUrl + this.GET_ACCOUNT_SETTINGS_ENDPOINT)
                .then();
    }

    //Sets some values that users are able to set under the "Account" tab of their settings page
    public ValidatableResponse updateProfile(String location, String url, String description) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("location", location).param("url", url).param("description", description)
                .when().post(this.baseUrl + this.POST_ACCOUNT_UPDATE_PROFILE_ENDPOINT)
                .then();
    }

    //returns saved search queries
    public ValidatableResponse returnSavedSearchList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_SAVED_SEARCHES_LIST_ENDPOINT)
                .then();
    }

    //Retrieve the information for the saved search represented by the given id
    public ValidatableResponse showSavedSearches(Long savedSearchID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", savedSearchID)
                .when().get(this.baseUrl + this.GET_SAVED_SEARCHES_SHOW_ENDPOINT)
                .then();
    }

    //Create a new saved search for user
    public ValidatableResponse createASavedSearch(String query) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("query", query)
                .when().post(this.baseUrl + this.CREATE_SAVED_SEARCHES_ENDPOINT)
                .then();
    }

    //Destroy a saved search for user
    public ValidatableResponse deleteASavedSearch(Long queryID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", queryID)
                .when().post(this.baseUrl + this.DELETE_SAVED_SEARCH_ENDPOINT)
                .then();
    }


}
