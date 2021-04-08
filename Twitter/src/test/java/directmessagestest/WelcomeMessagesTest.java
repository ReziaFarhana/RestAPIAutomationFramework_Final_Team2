package directmessagestest;

import directmessages.WelcomeMessages;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WelcomeMessagesTest {
    private WelcomeMessages welcomeMessages;

    @BeforeClass
    public void setUpTweetAPI() {
        this.welcomeMessages = new WelcomeMessages();
    }

  @Test
  public void testUserCanCreateWelcomeMessage(){
      ValidatableResponse response = this.welcomeMessages.createWelcomeMessage("Welcome to restAPI!","Yup I did it");
      System.out.println(response.extract().body().asPrettyString());
    }




















}
