package tweettest;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.PostRetrieveAndEngageWithTweets;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PostRetrieveAndEngageWithTweetsTest {

    private PostRetrieveAndEngageWithTweets tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new PostRetrieveAndEngageWithTweets();
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
    public void testUserCannotRetweet() {
        String errorMessage = "You have already retweeted this Tweet.";
        ValidatableResponse response = this.tweetAPIClient.reTweet(1379151742647042050l, "Israt Reto");
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, errorMessage, "Tweet is not a match");

    }

    @Test //7
    public void testUserCanReTweetAnotherUsersTweet() {
        String reTweet = "RT @elonmusk: The Earth is not flat, it’s a hollow globe &amp; Donkey King lives there!";
        ValidatableResponse response = this.tweetAPIClient.reTweet(1379026401341419520l, "Israt Reto");
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, reTweet, "Tweet is not a match");

    }

    @Test //7
    public void testUserCanReTweetAnotherUsersTweet1() {
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

    @Test //11
    public void testUserUnReTweetAReTweet(){
        String unReTweet = "BOOTCAMP tweeting starts NOW!!";
        ValidatableResponse response = this.tweetAPIClient.unReTweet(1379151742647042050l);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, unReTweet);
    }

    @Test //12
    public void testUserCanRetrieveASingleTweet(){
        String tweet = "I am tweeting my life way37246cc8-453e-48ad-bd05-4dded1ff16b1";
        ValidatableResponse response = this.tweetAPIClient.showSingleTweet(1379152479183601664l);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet);
    }

    @Test //13
    public void testUserCanGetFavoritesList() {
        List<Integer> expectedFavoriteCount = Arrays.asList(1,1,1,1,1);
        Response response = this.tweetAPIClient.favoritesList("Easha Khanam");
        //System.out.println(response.body().asPrettyString());
        List<String> actualFavoriteCount = response.jsonPath().getList("favorite_count");
        Assert.assertEquals(actualFavoriteCount,expectedFavoriteCount);
    }

    @Test //14
    public void testUserCanRetrieveRecentReTweets(){
        List<Integer> expectedCount = Arrays.asList(3,3,3);
        Response response = this.tweetAPIClient.collectionOfRecentReTweets(1379104603040534533l);
        //System.out.println(response.body().asPrettyString());
        List<String> actualCount = response.jsonPath().getList("retweet_count");
        //System.out.println(actualCount);
        Assert.assertEquals(actualCount,expectedCount);
    }

    @Test //15
    public void testUserCanRetrieveTweetsReTweetedByOthers(){
        List<Boolean> reTweetedStatus = Arrays.asList(false,false,false,true,true,false);
        Response response = this.tweetAPIClient.reTweetsOfMe("Israt Reto");
        System.out.println(response.body().asPrettyString());
        List<Boolean> actualRetweetedStatus = response.jsonPath().getList("retweeted");
        System.out.println(actualRetweetedStatus);
        Assert.assertEquals(actualRetweetedStatus,reTweetedStatus);
    }

    @Test //16
    public void testUserCanRetrieveUsersWhoRetweetedTheTweet(){
        List<Long> expectedUserIds = Arrays.asList(1376052489028521991L,
                1376273647225167881L,
                1376012108211613700L);
        ValidatableResponse response = this.tweetAPIClient.retrieveReTweeters(1379104603040534533L);
        //System.out.println(response.extract().body().asPrettyString());
        List<Long> actualUserIds = response.extract().body().jsonPath().getList("ids");
        Assert.assertEquals(actualUserIds,expectedUserIds, "List contains wrong ids");
    }

    @Test //17
    public void testUserCanRetrieveRelevenTweets(){
        Long tweetID = 1379463921577324546L;
        ValidatableResponse response = this.tweetAPIClient.retrieveReleventTweets("Sheikh Hasina","popular");
        //System.out.println(response.extract().body().asPrettyString());
        Long actualTweetID = response.extract().body().path("statuses[1].id");
        Assert.assertEquals(actualTweetID,tweetID,"Tweet does not exist");
    }



//    @Test //13
//    public void testUserCanRetrieveATweetsInBulk(){
//        List<Long> listOfTweets = new ArrayList<Long>();
//        listOfTweets.add(1379105722546462720L);
//        listOfTweets.add(1379104603040534533L);
//        System.out.println(listOfTweets);
//
////        String tweet = "I am tweeting my life way37246cc8-453e-48ad-bd05-4dded1ff16b1";
//        Response response = this.tweetAPIClient.showTweetsInBulk("Israt Reto",listOfTweets.subList(0,1));
//        System.out.println(response.body().asPrettyString());
////        String actualTweet = response.extract().body().path("text");
////        Assert.assertEquals(actualTweet, tweet);
//    }

















//        @Test //14
//    public void testGetUserTimeTweet() {
//        //List<Integer> expectedStatusCount = "16";
//        ValidatableResponse response = this.tweetAPIClient.getUserTimeTweet();
//        System.out.println(response.extract().body().asPrettyString());
//        List<Integer> actualStatusCount = response.extract().body().jsonPath().getList("statuses_count");
//            System.out.println(actualStatusCount);
////        Assert.assertEquals(actualStatusCount,expectedStatusCount);
//    }

//    List<Integer> expectedCount = Arrays.asList(2,2);
//    Response response = this.tweetAPIClient.collectionOfRecentReTweets(1379105722546462720l);
//        System.out.println(response.body().asPrettyString());
//    List<String> actualCount = response.jsonPath().getList("retweet_count");
//        System.out.println(actualCount);
//        Assert.assertEquals(actualCount,expectedCount);

















































//    @Test //10
//    public void testGetUserTimeTweet() {
//        String expectedStatusCount = "16";
//        ValidatableResponse response = this.tweetAPIClient.getUserTimeTweet();
//        System.out.println(response.extract().body().asPrettyString());
//        String actualStatusCount = response.extract().body().path("statuses_count");
//        Assert.assertEquals(actualStatusCount,expectedStatusCount);
//    }
//    @Test //10
//    public void testGetUserTimeTweet() {
//        Integer expectedFriendsCount = 11;
//        ValidatableResponse response = this.tweetAPIClient.getUserTimeTweet();
//        System.out.println(response.extract().body().asPrettyString());
//        ArrayList<Integer> actualFriendsCount = response.extract().body().path("statuses_count");
//        System.out.println(actualFriendsCount);
//        //Assert.assertEquals(actualFriendsCount,expectedFriendsCount);
//    }

    @Test
    public void testResponseTime() {
        Integer expectedResponeTime = 2553;
        ValidatableResponse response = this.tweetAPIClient.responseTime();
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testHeaderValue() {
        this.tweetAPIClient.headerValue();
    }


}
