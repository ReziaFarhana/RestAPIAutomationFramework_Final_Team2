package directmessages;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class WelcomeMessages extends RestAPI {
    private final String POST_WELCOME_MESSAGES_ENDPOINT = "/direct_messages/welcome_messages/new.json";

    public ValidatableResponse createWelcomeMessage(String welcomeMessageName, String text){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).header("Content-type", "application/json")
                .body("{\"name\": \""+welcomeMessageName+"\", \"welcome_message\": {\"message_data\": {\"text\": \""+text+"\", \"attachment\": {\"type\": \"media\", \"media\": {\"id\": \"48909183894931\"}}}}}")
                .when().get(this.baseUrl + this.POST_WELCOME_MESSAGES_ENDPOINT).then();
    }
}
