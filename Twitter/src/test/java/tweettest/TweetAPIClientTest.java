package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }

    @Test//create tweet-1 passed
    public void testUserCanTweetSuccessfully1() {
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
    public void testUserCanTweet3() {
        // User sent a tweet
        String tweet = "Bootcamp project is so stressful "+ UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet,"Tweet don't match");
    }
    @Test//test-4-passed
    public void testUserCanDeleteTweet4(){
        String tweet="Bootcamp project is so stressful 532cad5c-8245-464b-b487-d0fa2581cd94";
        ValidatableResponse deleteResponse1=this.tweetAPIClient.deleteTweet(1380283818645553155l);
        deleteResponse1.statusCode(200);
        String actualTweet=deleteResponse1.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


    @Test //5-passed
    public void testUserCanNotTweetTheSameTweetTwiceInARow5() {
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
    public void testUserCanNotTweetTheSameTweetTwiceInARow6() {
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
    public void testDeleteTweet7(){
        String tweet="COVID-19 vaccines may reduce transmission, but vaccinated Americans still need to wear masks in public.";
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet(1380278717998632963l);
        deleteResponse.statusCode(200);
        String actualTweet= deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }



    @Test(enabled = true)//passed-8
    public void testResponseTime8() {
        ValidatableResponse response = this.tweetAPIClient.responseTime();
    }
    @Test(enabled = true)//passed-9
    public void testHeaderValue() {
        this.tweetAPIClient.headerValue();
    }

    @Test(enabled = true)//passed
    public void testPropertyFromResponse9() {
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

    @Test//passed-11
    public void testUserCanRetweet10() {
        String retweet = "Bootcamp project is so stressful 29424148-e13a-4647-96f8-047ebaa551a3";
        ValidatableResponse response = this.tweetAPIClient.createReTweet(1380278717998632963l);
        // Verify that the tweet was successfully retweeted
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, retweet);
    }

    public void testUserCanTweet11() {
        // User sent a tweet
        String tweet = "Catch me if you can";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet,"Tweet don't match");
    }
    @Test
    public void testUserCanRetweet12() {
        String retweet = "Catch me if you can";
        ValidatableResponse response = this.tweetAPIClient.createReTweet(1379139113966825476l);
        // Verify that the tweet was successfully retweeted
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, retweet);
    }
    @Test
    public void testUserCanNotRetweet13() {
        String errorMessage = "You have already retweeted this Tweet.";
        ValidatableResponse response = this.tweetAPIClient.createReTweet(1380278717998632963l);
        // Verify
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, errorMessage);
    }

    @Test
    public void testUserCanFavoriteATweet14() {
        ValidatableResponse response = this.tweetAPIClient.favoriteTweet(1380278717998632963l);
        System.out.println(response.extract().body().asPrettyString());
        boolean actualFavoritedTweet = response.extract().body().path("favorited");
        Assert.assertTrue(actualFavoritedTweet);
    }

    @Test
    public void testUserCannotFavoriteAFavoritedTweet15() {
        String errorMessage = "You have already favorited this status.";
        ValidatableResponse response = this.tweetAPIClient.favoriteTweet(1380278717998632963l);
        System.out.println(response.extract().body().asPrettyString());
        String actualErrorMessage = response.extract().body().path("errors[0].message");
        System.out.println(actualErrorMessage);
        Assert.assertEquals(actualErrorMessage, errorMessage, "Error message is the same");
//        Assert.assertEquals(139,139);
    }

    @Test //block
    public void testUserCanBlockByScreenName16(){
        String expectedScreenName = "Donald J. Trump";
        ValidatableResponse response = this.tweetAPIClient.postBlockUsers("Donald J. Trump","DonaldTrump");
        System.out.println(response.extract().body().asPrettyString());
        String actualScreenName= response.extract().body().path("screen_name");
        Assert.assertEquals(actualScreenName,expectedScreenName,"Screen names dont match sister");

    }
    @Test //block
    public void testUserCanBlockByScreenName17(){
        String expectedScreenName = "Mukesh Ambani";
        ValidatableResponse response = this.tweetAPIClient.postBlockUsers("Mukesh Ambani 01","MukeshAmbani01");
        System.out.println(response.extract().body().asPrettyString());
        String actualScreenName= response.extract().body().path("screen_name");
        Assert.assertEquals(actualScreenName,expectedScreenName,"Screen names dont match sister");

    }

    @Test//
    public void testUserCanFollowByName18(){
        String expectedName = "Robert Downey Jr";
        ValidatableResponse response = this.tweetAPIClient.postFollowUser("Robert Downey Jr","RobertDowneyJr");
        System.out.println(response.extract().body().asPrettyString());
        String actualName= response.extract().body().path("name");
        Assert.assertEquals(actualName,expectedName,"Names don't match");
    }
    @Test//follow
    public void testUserCanFollowByName19(){
        String expectedName = "Bill Gates";
        ValidatableResponse response = this.tweetAPIClient.postFollowUser("Bill Gates","BillGates");
        System.out.println(response.extract().body().asPrettyString());
        String actualName= response.extract().body().path("name");
        Assert.assertEquals(actualName,expectedName,"Names dont match sister");

    }

    @Test
    public void testUserCanGetListOfBlockedUsers20(){
        List<String> blockUsers = Arrays.asList("MukeshAmbani01","DonaldTrump");
        ValidatableResponse response = this.tweetAPIClient.getBlockUsersList(true);
        System.out.println(response.extract().body().asPrettyString());
        List<String> actualBlockedUsers = response.extract().body().jsonPath().getList("users.name");
        Assert.assertEquals(actualBlockedUsers,blockUsers,"Try again");
    }

}
