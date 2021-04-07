package directmessagestest;

import directmessages.SendingAndReceivingEvents;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class SendingAndReceivingEventsTest {
    private SendingAndReceivingEvents sendingAndReceivingEvents;

    @BeforeClass
    public void setUpTweetAPI() {
        this.sendingAndReceivingEvents = new SendingAndReceivingEvents();
    }

    @Test //1
    public void testUserCanCreateDirectMessages() {
        String messageText = "YEAAA!! Welcome to my world!!!";
        ValidatableResponse response = this.sendingAndReceivingEvents.createDirectMessages(1376012108211613700L, messageText);
//        System.out.println(response.extract().body().asPrettyString());
        String actualMessageText = response.extract().body().path("event.message_create.message_data.text");
        Assert.assertEquals(actualMessageText, messageText, "Text does not exist");
    }

    @Test //2
    public void testUserCanGetListOfDirectMessages(){
        List<String> directMessagesList = Arrays.asList("YEAAA!! Welcome to my world!!!", "YEAAA!! Welcome to my world!!!", "YEAAA!! Welcome to my world!!!", "Welcome to my world!!!", "Hello World!", "sending direct message");
        ValidatableResponse response = this.sendingAndReceivingEvents.getDirectMessagesList();
        System.out.println(response.extract().body().asPrettyString());
        List<String> actualDirectMessagesList = response.extract().body().jsonPath().getList("events.message_create.message_data.text");
        System.out.println(actualDirectMessagesList);
        Assert.assertEquals(actualDirectMessagesList,directMessagesList, "List does not contain the same information");
    }

    @Test //3
    public void testUserCanDeleteDirectMessages(){
        ValidatableResponse response = this.sendingAndReceivingEvents.deleteDirectMessages(1379933676079824901L);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
    }

}
