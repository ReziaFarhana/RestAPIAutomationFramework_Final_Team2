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


    @Test //1
    public void testUserCanTweetSuccessfully() {
        String tweet = "BOOTCAMP tweeting starts NOW!!";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet don't match");
    }

    @Test //2
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        String tweet = "BOOTCAMP tweeting starts NOW!!";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage = "Status is a duplicate.";
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, expectedMessage, "Tweet match");
        Assert.assertNotEquals("403", 200);
    }

    @Test //3
    public void testUserCanTweetRandomSuccessfully() {
        String tweet = "I am tweeting my life way" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet don't match");
    }

    @Test //4
    public void testUserCanDeleteTweet() {
        String tweet = "I am tweeting my life way05daefcb-8171-442a-89c1-c3081de31aae";
        ValidatableResponse deleteResponse = this.tweetAPIClient.deleteTweet(1379152553812836352l);
        deleteResponse.statusCode(200);
        String actualTweet = deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }

    @Test //5
    public void testUserCanRetweet() {
        String tweet = "RT @IsratReto: BOOTCAMP tweeting starts NOW!!";
        ValidatableResponse response = this.tweetAPIClient.reTweet(1379151742647042050l, "Israt Reto");
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet is not a match");
    }

    @Test //6
    public void testUserCannotRetweet(){
        String errorMessage = "You have already retweeted this Tweet.";
        ValidatableResponse response = this.tweetAPIClient.reTweet(1379151742647042050l, "Israt Reto");
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, errorMessage, "Tweet is not a match");

    }

    @Test //7
    public void testUserCanReTweetAnotherUsersTweet(){
        String reTweet = "RT @elonmusk: The Earth is not flat, it’s a hollow globe &amp; Donkey King lives there!";
        ValidatableResponse response = this.tweetAPIClient.reTweet(1379026401341419520l, "Israt Reto");
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, reTweet, "Tweet is not a match");

    }  @Test //7
    public void testUserCanReTweetAnotherUsersTweet1(){
        String reTweet = "RT @eashaarap: Cant Post Tweet Twice";
        ValidatableResponse response = this.tweetAPIClient.reTweet(1379139113966825476l, "Israt Reto");
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, reTweet, "Tweet is not a match");

    }

    @Test //8
    public void testUserCanFavoriteATweet() {
        ValidatableResponse response = this.tweetAPIClient.favoriteTweet(1378881525438148608l);
        System.out.println(response.extract().body().asPrettyString());
        boolean actualFavoritedTweet = response.extract().body().path("favorited");
        Assert.assertTrue(actualFavoritedTweet);
    }

    @Test //9
    public void testUserCannotFavoriteAFavoritedTweet() {
        String errorMessage = "You have already favorited this status.";
        ValidatableResponse response = this.tweetAPIClient.favoriteTweet(1378886585517805568l);
        System.out.println(response.extract().body().asPrettyString());
        String actualErrorMessage = response.extract().body().path("errors[0].message");
        System.out.println(actualErrorMessage);
        Assert.assertEquals(actualErrorMessage, errorMessage, "Error message is the same");
//        Assert.assertEquals(139,139);
    }

    @Test //10
    public void testUserUnFavoriteAFavoritedTweet() {
        ValidatableResponse response = this.tweetAPIClient.unfavoriteTweet(1378881525438148608l);
        System.out.println(response.extract().body().asPrettyString());
        boolean actualFavoritedTweet = response.extract().body().path("favorited");
        Assert.assertFalse(actualFavoritedTweet);
    }

    @Test //9
    public void testUserCanGetFavoritesList() {
        ValidatableResponse response = this.tweetAPIClient.favoritesList("eashaarap");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testUserCanGetFavoritesList1() {
        ValidatableResponse response = this.tweetAPIClient.favoritesList1(1376273647225167881l);
        System.out.println(response.extract().body().asPrettyString());
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
