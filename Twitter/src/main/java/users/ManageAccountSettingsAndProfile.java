package users;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class ManageAccountSettingsAndProfile extends RestAPI {
    private final String GET_ACCOUNT_SETTINGS_ENDPOINT = "/account/settings.json";
    private final String GET_ACCOUNT_VERIFY_CREDENTIALS_ENDPOINT = "/account/verify_credentials.json";
    private final String GET_USERS_PROFILE_BANNER_ENDPOINT = "/users/profile_banner.json";
    private final String POST_ACCOUNT_REMOVE_PROFILE_BANNER_ENDPOINT = "/account/remove_profile_banner.json";

    //returns user settings
    public ValidatableResponse returnUserSettings() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_ACCOUNT_SETTINGS_ENDPOINT)
                .then();
    }

    //Check whether user credential is valid
    public ValidatableResponse verifyCredential(String name, boolean includeEmail) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("name",name).param("include_email",includeEmail)
                .when().get(this.baseUrl + this.GET_ACCOUNT_VERIFY_CREDENTIALS_ENDPOINT)
                .then();
    }

    //Check user's profile banner
    public ValidatableResponse verifyProfileBanner(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("screen_name",screenName)
                .when().get(this.baseUrl + this.GET_USERS_PROFILE_BANNER_ENDPOINT)
                .then();
    }

    //Removes the uploaded profile banner
    public ValidatableResponse removeProfileBanner() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().post(this.baseUrl + this.POST_ACCOUNT_REMOVE_PROFILE_BANNER_ENDPOINT)
                .then().statusCode(200);
    }



















}
