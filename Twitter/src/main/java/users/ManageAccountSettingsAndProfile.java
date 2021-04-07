package users;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class ManageAccountSettingsAndProfile extends RestAPI {
    private final String GET_ACCOUNT_SETTINGS_ENDPOINT = "/account/settings.json";
    private final String GET_ACCOUNT_VERIFY_CREDENTIALS_ENDPOINT = "/account/verify_credentials.json";

    //returns user settings
    public ValidatableResponse returnUserSettings() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_ACCOUNT_SETTINGS_ENDPOINT)
                .then();
    }

    //
}
