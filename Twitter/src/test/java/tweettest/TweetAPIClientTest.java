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

    @Test
    public void testUserCanTweetSuccessfully() {
        // User sent a tweet
        String tweet = "We are learning Rest API using Rest Assured and our this is my First Tweet" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        // Verify that the tweet is successful
        response.statusCode(200);
        // Verity tweet value
        String actualTweet = response.extract().body().path("text");
        Long id = response.extract().body().path("id");
        System.out.println(id);
        Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
    }

    public void testUserCanTweetSuccessfully3() {
        // User sent a tweet
        String tweet = "Today is My Day " + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        // Verify that the tweet is successful
        response.statusCode(200);
        // Verity tweet value
        String actualTweet = response.extract().body().path("text");
        Long id = response.extract().body().path("id");
        System.out.println(id);
        Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
    }


    @Test
    public void testUserCanNotTweetTwiceInARow() {
        // User sent a tweet
        String tweet = "We are learning Rest API using Rest Assured and our First Tweet87dc3c71-a296-468b-baf3-1ac36f1e47e5";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage = "Status is a duplicate.";
        String actualTweet = response.extract().body().path("errors[0].message");
       Assert.assertEquals(actualTweet, expectedMessage, "Tweet is match");
       Assert.assertNotEquals("403", 200);
    }

    @Test
    public void testUserCanTweetSuccessfully2() {
        // User sent a tweet
        String tweet = "Hola! "+ UUID.randomUUID().toString();
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
        String tweet = "Bootcamp project is so stressful "+ UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet,"Tweet don't match");
    }
    @Test//test-4-failed
    public void testUserCanDeleteTweet1(){
        String tweet="We are learning Rest API using Rest Assured and our First Tweet295ba966-1e9a-4628-a8bb-e1a70f6bf8a0";
        ValidatableResponse deleteResponse1=this.tweetAPIClient.DeleteTweet(1378047777687678985l);
        deleteResponse1.statusCode(200);
        String actualTweet=deleteResponse1.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test
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


    @Test(enabled = false)
    public void testResponseTime() {
        ValidatableResponse response = this.tweetAPIClient.responseTime();
    }
    @Test(enabled = false)
    public void testHeaderValue() {
        this.tweetAPIClient.headerValue();
    }

    @Test(enabled = true)
    public void testPropertyFromResponse() {
        //1. User send a tweet
        String tweet = "We are learning Rest API Automation with restApiAutomation_Final_" +
                "the hard work is coming yall!!!!!" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        //2. Verify that the tweet was successful
        // response.statusCode(200);

        //this.tweetAPIClient.checkProperty();
        //JsonPath pathEvaluator=response.;
        //System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().asPrettyString().contains("id"));

        //String actualTweet = response.extract().body().path("text");
        //Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
    }



}
