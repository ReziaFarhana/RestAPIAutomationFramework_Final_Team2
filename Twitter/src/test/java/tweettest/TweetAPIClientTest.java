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

    @Test//create tweet-1 passed
    public void testUserCanTweetSuccessfully() {
        // User sent a tweet
        String tweet = "I Just Tweeted...yeahhh";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(200);
        // Verify tweet value
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet,"Tweet don't match");
    }


    @Test//create tweet-2 passed
    public void testUserCanTweetSuccessfully2() {
        // User sent a tweet
        String tweet = "Thank God it is so sunny today!";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        // Verify that the tweet is successful
        response.statusCode(200);
        // Verity tweet value
        String actualTweet=response.extract().body().path("text");
        Long id= response.extract().body().path("id");
        System.out.println(id);
        Assert.assertNotEquals("id","text");
    }
    @Test//create tweet-3 passed
    public void testUserCanTweet() {
        // User sent a tweet
        String tweet = "I am still working on API "+ UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet,"Tweet don't match");
    }
    @Test//test-4-failed
    public void testUserCanDeleteTweet(){
        String tweet="Thank God it is so sunny today!";
        ValidatableResponse deleteResponse1=this.tweetAPIClient.deleteTweet( 1380649917060939778l);
        deleteResponse1.statusCode(200);
        String actualTweet=deleteResponse1.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


    @Test //5-passed
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        // User sent a tweet
        String tweet = "We are learning Rest API using Rest Assured and our Tweet is same tweet";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        //response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage="Status is a duplicate.";
        String actualTweet=response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet,expectedMessage,"Tweet match");
        Assert.assertNotEquals("403",200);
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
        String tweet="I hope this tweet finds its page :)";
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
   @Test
public void testShowsStatus(){
    String tweet="I hope this tweet finds its page :)";
    ValidatableResponse response=this.tweetAPIClient.showStatus(1380700533518966785L);
    System.out.println(response.extract().body().asPrettyString());
    response.statusCode(200);
    String actuaTweet=response.extract().body().path("text");
    Assert.assertEquals(actuaTweet,tweet,"Tweet did not match");

}






//    @Test(enabled = false)
//    public void testResponseTime() {
//        ValidatableResponse response = this.tweetAPIClient.responseTime();
//    }
//    @Test(enabled = false)
//    public void testHeaderValue() {
//        this.tweetAPIClient.headerValue();
//    }
//
//    @Test(enabled = false)
//    public void testPropertyFromResponse() {
//        //1. User send a tweet
//        String tweet = "We are learning Rest API Automation with restApiAutomation_Final_Farhana" + UUID.randomUUID().toString();
//        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
//        //2. Verify that the tweet was successful
//        // response.statusCode(200);
//
//        //this.tweetAPIClient.checkProperty();
//        //JsonPath pathEvaluator=response.;
//        //System.out.println(response.extract().body().asPrettyString());
//        System.out.println(response.extract().body().asPrettyString().contains("id"));
//
//        //String actualTweet = response.extract().body().path("text");
//        //Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
//    }



}
