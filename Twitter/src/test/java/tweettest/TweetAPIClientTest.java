package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;


    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }

    @Test //Tweet number 1
    public void testUserCanTweetSuccessfully() {
        String tweet = "I am learning Rest API This is my First Tweet . " + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Long id = response.extract().body().path("id");
        System.out.println(id);
        Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
    }
@Test
    public void testUserCanTweetSuccessfully3() {

        String tweet = "Today is a Beautiful Day " + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Long id = response.extract().body().path("id");
        System.out.println(id);
        Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
    }


    @Test
    public void testUserCanNotTweetTwiceInARow() {
        String tweet = "This is Two Tweets in a Row";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(403);
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage = "Status is a duplicate.";
        String actualTweet = response.extract().body().path("errors[0].message");
       Assert.assertEquals(actualTweet, expectedMessage, "Tweet is match");
       Assert.assertNotEquals("403", 200);
    }

    @Test
    public void testUserCanTweetSuccessfully2() {
        String tweet = "Hola! "+ UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Long id= response.extract().body().path("id");
        System.out.println(id);
        Assert.assertNotEquals("id","text");
    }
    @Test
    public void testUserCanTweet() {
        String tweet = "I need a vacation ! "+ UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet,"Tweet don't match");
    }
    @Test
    public void testUserCanDeleteTweet1(){
        String tweet="";
        ValidatableResponse deleteResponse1=this.tweetAPIClient.DeleteTweet(1380914752621318145L);
        deleteResponse1.statusCode(200);
        String actualTweet=deleteResponse1.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        String tweet = "We are learning Rest API using Rest Assured and our Tweet is same tweet";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage="Status is a duplicate.";
        String actualTweet=response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet,expectedMessage,"Tweet match");
        Assert.assertNotEquals("403",200);
    }
    @Test(enabled = true)
    public void testPropertyFromResponse() {
        String tweet = "We are learning Rest API Automation with restApiAutomation_Final_" +
                "the hard work is coming yall!!!!!" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString().contains("id"));
    }

    @Test//test-4-failed
    public void testUserCanDeleteTweet(){
        String tweet=" I love Summer Time!";
        ValidatableResponse deleteResponse1=this.tweetAPIClient.deleteTweet( 1380649917060939778l);
        deleteResponse1.statusCode(200);
        String actualTweet=deleteResponse1.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test
    public void testUserIsableToCreateAnotherTweet(){
        String tweet="I am practicing RestApi Tonight";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet,"Tweets don't match");

    }
    @Test
    public void userCanTweetTheMessageOnceMore(){
        String tweet="Trying to tweet once more";
        ValidatableResponse response=this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet,"tweet did not match");
    }
    @Test
    public void userShouldDeleteTweet(){
        String tweet="I am still working on API 8c5fcfc1-d502-4f52-985e-7da4a4580271";
        ValidatableResponse response=this.tweetAPIClient.deleteTweet(1380700880329129989L);
        response.statusCode(200);
        String actuaTweet=response.extract().body().path("text");
        Assert.assertEquals(actuaTweet,tweet,"Tweet did not match");
    }


}
