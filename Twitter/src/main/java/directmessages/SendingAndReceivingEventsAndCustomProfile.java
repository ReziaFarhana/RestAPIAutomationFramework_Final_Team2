package directmessages;

import base.RestAPI;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SendingAndReceivingEventsAndCustomProfile extends RestAPI {
    private final String CREATE_DIRECT_MESSAGES_ENDPOINT = "/direct_messages/events/new.json";
    private final String GET_DIRECT_MESSAGES_LIST_ENDPOINT = "/direct_messages/events/list.json";
    private final String DELETE_DIRECT_MESSAGES_ENDPOINT = "/direct_messages/events/destroy.json";




//    public ValidatableResponse createDirectMessages(String typeOfMessage, long recipientID, String text){
//        Map<String,String> map = new HashMap<>();
//        map.put("type",typeOfMessage);
//        map.put("message_create.message_data.text",text);
//        //map.put("text",text);
//        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
//                .param("message_create.target.recipient_id", recipientID).params(map)
//                .when().post(this.baseUrl + this.CREATE_DIRECT_MESSAGES_ENDPOINT)
//                .then();
//    }

    public ValidatableResponse createDirectMessages(Long recipientID,String message){
        String body = "{\"event\": {\"type\": \"message_create\", \"message_create\": {\"target\": {\"recipient_id\": \""+recipientID+"\"}, \"message_data\": {\"text\": \""+message+"\"}}}}";
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).header("Content-type","application/json").and()
                .body(body)
                .when().post(this.baseUrl + this.CREATE_DIRECT_MESSAGES_ENDPOINT).then().extract().response().then();
    }

    //returns all direct message
    public ValidatableResponse getDirectMessagesList(){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_DIRECT_MESSAGES_LIST_ENDPOINT).then();
    }

    //delete direct messages
    public ValidatableResponse deleteDirectMessages(long eventID){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id",eventID)
                .when().delete(this.baseUrl + this.DELETE_DIRECT_MESSAGES_ENDPOINT).then();
    }

    //
}
