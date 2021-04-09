package tweettest;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.net.HttpURLConnection;
import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;
    private String expectedWBPath = System.getProperty("user.dir")+"\\src\\test\\resources\\Test_Data.xlsx";

    ValidatableResponse response;
    ResponseSpecification checkStatusCodeAndContentType = new ResponseSpecBuilder()
            .expectStatusCode(HttpURLConnection.HTTP_OK)
            .expectContentType(ContentType.JSON).build();


    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }

    @Test
    public void testUserCanTweetSuccessfully() {
        // User sent a tweet
        String tweet = "Tweet2- Hello World! Rest API is so Cool!";
        response = this.tweetAPIClient.createTweet(tweet);

        // To check the response in the console
        System.out.println(response.extract().body().asPrettyString());

        // Verify that the tweet is successful
        response.statusCode(200);
        // Verify tweet value
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet don't match");
    }

    @Test
    public void testUserCanTweetSuccessfully2() {
        // User sent a tweet
        // UUID.randomUUID() come from java util to generate random id at the end of tweet
        String tweet = "Tweet3- Hello World! Rest API is so Cool!" + UUID.randomUUID().toString();
        response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        // Verify that the tweet is successful
        response.statusCode(200);
        // Verity tweet value
        String actualTweet = response.extract().body().path("text");
        Long id = response.extract().body().path("id");
        System.out.println(id);
        Assert.assertNotEquals("id", "text");
    }

    @Test
    public void testUserCanTweet() {
        // User sent a tweet
        String tweet = "Bootcamp project is so stressful " + UUID.randomUUID().toString();
        response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");

        // Wanna print the id so I can use on the next test case to testUserCanDelete
        Long id= response.extract().body().path("id");
        System.out.println(id);
        Assert.assertEquals(actualTweet, tweet, "Tweet don't match");
    }

    @Test
    public void testUserCanTweet2() {
        // User sent a tweet   id=1380473768716296200
        String tweet = "Good software, like wine, takes time." + UUID.randomUUID().toString();
        response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");

        // Wanna print the id so I can use on the next test case to testUserCanDelete
        Long id= response.extract().body().path("id");
        System.out.println(id);
        Assert.assertEquals(actualTweet, tweet, "Tweet don't match");
    }

    @Test
    public void testUserCanDeleteTweet() {
        String tweet = "Bootcamp project is so stressful 311c64d3-1720-4ba0-9642-9dfa38e9f40b";
        ValidatableResponse deleteResponse1 = this.tweetAPIClient.deleteTweet(1380439692517109762l);
        deleteResponse1.statusCode(200);
        String actualTweet = deleteResponse1.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }

    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        // User sent a tweet
        String tweet = "Tweet5- Hello World! Rest API is so Cool!";
        response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage = "Status is a duplicate.";
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, expectedMessage, "Tweet match");
        Assert.assertNotEquals("403", 200);
    }

    @Test
    public void testUserCanReply() {
//        String retweet = "";
//        response = this.tweetAPIClient.createReTweet(1379139113966825476l);
//        // Verify that the tweet was successfully retweeted
//        System.out.println(response.extract().body().asPrettyString());
//        String actualTweet = response.extract().body().path("text");
//        Assert.assertEquals(actualTweet, retweet);
    }


    @Test
    public void testUserCanRetweet() {
        String retweet = "RT @Parisa43885814: Good software, like wine, takes time.6ed67b1a-4766-4ea9-9f42-62de0fbb04a6";
        response = this.tweetAPIClient.createReTweet(1380473768716296200l);
        // Verify that the tweet was successfully retweeted
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, retweet, "Test Fail");
    }

    @Test
    public void testUserCanNotRetweet() {
        String errorMessage = "You have already retweeted this Tweet.";
        response = this.tweetAPIClient.createReTweet(1380439692517109762l);
        // Verify
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, errorMessage);
    }

    @Test
    public void testUserCanLikeATweet() {
        response = this.tweetAPIClient.likeATweet(1380439692517109762l);
        System.out.println(response.extract().body().asPrettyString());
        boolean actualLikedTweet = response.extract().body().path("favorited");
        Assert.assertTrue(actualLikedTweet);
    }

    @Test
    public void testUserCanNotReLikeATweet() {
        String errorMessage = "You have already favorited this status.";
        response = this.tweetAPIClient.likeATweet(1380439692517109762l);
        System.out.println(response.extract().body().asPrettyString());
        String actualErrorMessage = response.extract().body().path("errors[0].message");
        System.out.println(actualErrorMessage);
        Assert.assertEquals(actualErrorMessage, errorMessage, "Test Fail");
    }

    @Test
    public void testUserCanUnLikeATweet() {
        response = this.tweetAPIClient.unLikeATweet(1380439692517109762l);
        System.out.println(response.extract().body().asPrettyString());
        boolean actualLikedTweet = response.extract().body().path("favorited");
        Assert.assertFalse(actualLikedTweet);
    }

//Post
    @Test
    @Parameters("username")
    public void testUserCanFollow(@Optional("PrimeVideo") String username) {
        response = this.tweetAPIClient.postTwitterFollow(username);
        response.assertThat().spec(checkStatusCodeAndContentType);
        String expectedFollow = response.extract().response().body().jsonPath().getJsonObject("screen_name").toString();
        System.out.println("Followed: " + expectedFollow);
        Assert.assertEquals(username, expectedFollow, "Test Fail- Unable to follow");
    }


//POST
    @Test
    @Parameters ("username")
    public void testUserCanUnFollow(@Optional("RealJorg") String username) {
        response = this.tweetAPIClient.postTwitterUnFollow(username);
        response.assertThat().spec(checkStatusCodeAndContentType);
        String expectedUnFollow = response.extract().response().body().jsonPath().getJsonObject("screen_name").toString();
        System.out.println("Un-Followed: " + expectedUnFollow);
        Assert.assertEquals(username, expectedUnFollow, "Test Fail- Unable to unfollow");
    }








    @Test
    public void testUserCanGetTweetResponseTime(){
        response = this.tweetAPIClient.responseTime();
//        System.out.println(response.extract().body().asPrettyString());
        String actualResponseTime = response.extract().body().path("[0]");
        Assert.assertTrue(Boolean.parseBoolean(actualResponseTime));
    }





    @Test
    public void testUploadPic() {
        response = this.tweetAPIClient.uploadPic(TweetAPIClient.image());
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testUserCanPostTweetWithPic() {
        String tweet = "Woof Woof World! :) ";
        response = this.tweetAPIClient.postTweetWithPic(tweet, 1380455667123716099l);
        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals("200", 200);
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
