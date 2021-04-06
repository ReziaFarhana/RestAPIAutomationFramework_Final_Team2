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
        String tweet = "Learning Rest API using Rest Assured and our First Tweet";
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
        String tweet = "Learning Rest API using Rest Assured and our First Tweet"+ UUID.randomUUID().toString();
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
    @Test//test-4-passed
    public void testUserCanDeleteTweet(){
        String tweet="Bootcamp project is so stressful 532cad5c-8245-464b-b487-d0fa2581cd94";
        ValidatableResponse deleteResponse1=this.tweetAPIClient.deleteTweet(1378364509635670016l);
        deleteResponse1.statusCode(200);
        String actualTweet=deleteResponse1.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


    @Test //5-passed
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        // User sent a tweet
        String tweet = "COVID-19 vaccines may reduce transmission, but vaccinated Americans still need to wear masks in public.";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage="Status is a duplicate.";
        String actualTweet=response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet,expectedMessage,"Tweet match");

    }
    @Test //6-passed
    public void testUserCanNotTweetTheSameTweetTwiceInARow2() {
        // User sent a tweet
        String tweet = "COVID-19 vaccines may reduce transmission, but vaccinated Americans still need to wear masks in public.";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage="Status is a duplicate.";
        String actualTweet=response.extract().body().path("errors[0].message");
        Assert.assertNotEquals("403",200);//Negative Testing
    }


    @Test//7-passed
    public void testDeleteTweet(){
        String tweet="COVID-19 vaccines may reduce transmission, but vaccinated Americans still need to wear masks in public.";
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet(1378363760063184902l);
        deleteResponse.statusCode(200);
        String actualTweet= deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }



    @Test(enabled = true)//passed-8
    public void testResponseTime() {
        ValidatableResponse response = this.tweetAPIClient.responseTime();
    }
    @Test(enabled = true)//passed-9
    public void testHeaderValue() {
        this.tweetAPIClient.headerValue();
    }

    @Test(enabled = true)//passed-10
    public void testPropertyFromResponse() {
        //1. User send a tweet
        String tweet = "NYC Hits Single-Day Record Of 93,000 COVID Shots, As CDC Allows Travel For Fully Vaccinated" + UUID.randomUUID().toString();
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
