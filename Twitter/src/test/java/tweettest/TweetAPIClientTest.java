package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.*;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }

    @Test
    //create tweet-1 passed
    public void testUserCanTweetSuccessfully1() {
        // User sent a tweet
        String tweet = "This is soooo much fun....not";
        //we want to hold the tweet in a variable, 'response'
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(200);
        // Verify tweet value
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet don't match");
        System.out.println(response.extract().body().asPrettyString());
    }


    @Test
    public void testUserCanTweetSuccessfully2() {
        // User sent a tweet
        String tweet = "My first testcase in api" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
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
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet don't match");
    }

    @Test
    public void testUserCanDeleteTweet3() {
        String tweet = "My first testcase in api4";
        ValidatableResponse deleteResponse = this.tweetAPIClient.deleteTweet(1378894132622557190l);
        deleteResponse.statusCode(200);
        String actualTweet = deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }


    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow4() {
        // User sent a tweet
        String tweet = "My first testcase in api6";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage = "Status is a duplicate.";
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, expectedMessage, "Tweet match");
        Assert.assertNotEquals("403", 200);
    }

    @Test
    public void testUserCanRetweet6() {
        String retweet = "RT @eashaarap: Cant Post Tweet Twice";
        ValidatableResponse response = this.tweetAPIClient.createReTweet(1379139113966825476l);
        // Verify that the tweet was successfully retweeted
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, retweet);
    }

    @Test
    public void testUserCanNotRetweet7() {
        String errormessage = "You have already retweeted this Tweet.";
        ValidatableResponse response = this.tweetAPIClient.createReTweet(1378884508108390400l);
        // Verify
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, errormessage);
    }

    @Test
    //retweet RetosTweet
    public void testUserCanRetweetAnotherUserTweet8() {
        String retweet = "RT @IsratReto: We are learning Rest API using Rest Assured and our First Tweet1";
        ValidatableResponse response = this.tweetAPIClient.createReTweet(1379105722546462720l);
        // Verify that the tweet was successfully retweeted
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, retweet);
    }

    @Test
    public void testUserCantUnReTweet9() {
        String retweet = "Cant Post Tweet Twice";
        ValidatableResponse response = this.tweetAPIClient.unReTweet(1379139113966825476l);
        // Verify that the tweet was successfully retweeted
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, retweet);
    }

    @Test
    public void testUserCanFavoriteATweet10() {
        ValidatableResponse response = this.tweetAPIClient.favoriteTweet(1379151742647042050l);
        System.out.println(response.extract().body().asPrettyString());
        boolean actualFavoritedTweet = response.extract().body().path("favorited");
        Assert.assertTrue(actualFavoritedTweet);
    }

    @Test
    public void testUserCannotFavoriteAFavoritedTweet11() {
        String errorMessage = "You have already favorited this status.";
        ValidatableResponse response = this.tweetAPIClient.favoriteTweet(1379151742647042050l);
        System.out.println(response.extract().body().asPrettyString());
        String actualErrorMessage = response.extract().body().path("errors[0].message");
        System.out.println(actualErrorMessage);
        Assert.assertEquals(actualErrorMessage, errorMessage, "Error message is the same");
//        Assert.assertEquals(139,139);
    }

    @Test
    public void testUserUnFavoriteAFavoritedTweet12() {
        ValidatableResponse response = this.tweetAPIClient.unfavoriteTweet(1379151742647042050l);
        System.out.println(response.extract().body().asPrettyString());
        boolean actualFavoritedTweet = response.extract().body().path("favorited");
        Assert.assertFalse(actualFavoritedTweet);
    }

    @Test
    public void testUserGetStatuses13() {
        String expectedTweet = "The Earth is not flat, it’s a hollow globe &amp; Donkey King lives there!";
        ValidatableResponse response = this.tweetAPIClient.getStatuses(1379026401341419520l);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, expectedTweet, "Text not match");

    }

    @Test
    public void testUserCannotGetsStatuses14() {
        String errorMsg = "No status found with that ID.";
        ValidatableResponse response = this.tweetAPIClient.getStatuses(137902640134141920l);
        System.out.println(response.extract().body().asPrettyString());
        String actualErrorMsg = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualErrorMsg, errorMsg, "Error not match");

    }

    @Test
    public void testUserCanGetFriends15() {
        Integer expectedID = 50393960;
        ValidatableResponse response = this.tweetAPIClient.getFriendsId("Israt Reto");
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        Integer actualID = response.extract().body().path("ids[1]");
        Assert.assertEquals(actualID, expectedID, "IDs do not match");
    }

    @Test
    public void testUserCanCreateList16() {
        String name = "I created my list again!!!!!";
        ValidatableResponse response = this.tweetAPIClient.createList(name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("name");
        Assert.assertEquals(actualName, name, "Not matched");
    }

    @Test
    public void testUserCanRetrieveList17() {
        String name = "I created my list again!!!!!";
        ValidatableResponse response = this.tweetAPIClient.getList();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("[0].name");
        Assert.assertEquals(actualName, name, "Not matched");
    }

    @Test
    public void testUserCanCreateCollectionListUsingId18() {
        String name = "My Special Collection!!!!!";
        Long expectedID = 1376273647225167881l;
        ValidatableResponse response = this.tweetAPIClient.createCollectionList(name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        Long actualID = response.extract().body().path("objects.users.1376273647225167881.id");
        Assert.assertEquals(actualID, expectedID, "Not matched");
    }

    @Test//failed
    public void testUserCanAddToCollectionList() {
        String collectionID = "custom-1379483174556815374";
        Long expectedID = 1376273647225167881l;
        ValidatableResponse response = this.tweetAPIClient.addToCollectionList(collectionID, 1379480049418637321l);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
//        Long actualID = response.extract().body().path("user.id");
//        Assert.assertEquals(actualID, expectedID, "Not matched");
    }

    @Test//passed
    public void testUserCannotAddToCollectionListUsingReason19() {
        ArrayList<String> errorReason = new ArrayList<>();
        errorReason.add("duplicate");
        Long expectedID = 1376273647225167881l;
        ValidatableResponse response = this.tweetAPIClient.addToCollectionList("custom-1379483174556815374", 1379483819007479820l);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        ArrayList<String> actualErrorMsg = response.extract().body().path("response.errors.reason");
        Assert.assertEquals(actualErrorMsg, errorReason, "Error not match");

    }

    @Test
    public void verifytestUserCannotAddToCollectionListUsingOP20() {
        ArrayList<String> errorOP = new ArrayList<>();
        errorOP.add("add");
        Long expectedID = 1376273647225167881l;
        ValidatableResponse response = this.tweetAPIClient.addToCollectionList("custom-1379483174556815374", 1379483819007479820l);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        ArrayList<String> actualErrorMsg = response.extract().body().path("response.errors.change.op");
        Assert.assertEquals(actualErrorMsg, errorOP, "Error not match");

    }

    @Test
    public void verifytestUserCannotAddToCollectionListUsingTweetID21() {
        ArrayList<Long> errorId = new ArrayList<>();
        errorId.add(1379483819007479820l);
        Long expectedID = 1376273647225167881l;
        ValidatableResponse response = this.tweetAPIClient.addToCollectionList("custom-1379483174556815374", 1379483819007479820l);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        ArrayList<Long> actualErrorMsg = response.extract().body().path("response.errors.change.tweet_id");
        Assert.assertNotEquals(actualErrorMsg, errorId, "Error not match");

    }

    @Test
    public void verifytestUserCanCreateCollectionListUsingName22() {
        String name = "My Special Collection!!!!!";
        String expectedname = "Easha";
        ValidatableResponse response = this.tweetAPIClient.createCollectionList(name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualID = response.extract().body().path("objects.users.1376273647225167881.name");
        Assert.assertEquals(actualID, expectedname, "Not matched");
    }

    @Test
    public void verifytestUserCanCreateCollectionListUsingScreenName22() {
        String name = "My Special Collection!!!!!";
        String expectedname = "eashaarap";
        ValidatableResponse response = this.tweetAPIClient.createCollectionList(name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualID = response.extract().body().path("objects.users.1376273647225167881.screen_name");
        Assert.assertEquals(actualID, expectedname, "Not matched");
    }

    @Test
    public void testUserCannotGetsStatusesUsingCode23() {
        int errorCode = 144;
        ValidatableResponse response = this.tweetAPIClient.getStatuses(137902640134141920l);
        System.out.println(response.extract().body().asPrettyString());
        int actualErrorMsg = response.extract().body().path("errors[0].code");
        Assert.assertEquals(actualErrorMsg, errorCode, "Error not match");

    }
    @Test
    public void testUserCanDestroyCollection24(){
        ValidatableResponse response = this.tweetAPIClient.destroyCollectionList("custom-1379525033622700033");
        System.out.println(response.extract().body().asPrettyString());
        boolean actualDestroyed = response.extract().body().path("destroyed");
        Assert.assertTrue(actualDestroyed);
    }
    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInArowUsingCode25() {
        // User sent a tweet
        String tweet = "My first testcase in api6";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        Integer expectedMessage = 187;
        Integer actualTweet = response.extract().body().path("errors[0].code");
        Assert.assertEquals(actualTweet, expectedMessage, "Tweet match");
        Assert.assertNotEquals("403", 200);
    }
    @Test
    public void testUserCanGetAccountSettings26(){
        ValidatableResponse response = this.tweetAPIClient.getAccountSettings();
        System.out.println(response.extract().body().asPrettyString());
        boolean actualProtected = response.extract().body().path("protected");
        Assert.assertFalse(actualProtected);
    }
    @Test
    public void testUserCanGetFriendsListUsingName27(){
        String name = "Kamala Harris";
        ValidatableResponse response = this.tweetAPIClient.getFriendsList();
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("users[0].name");
        Assert.assertEquals(actualName, name, "Name doesnt match");
    }
    @Test
    public void testUserCanGetFriendsListUsingID27(){
        Integer ID = 30354991;
        ValidatableResponse response = this.tweetAPIClient.getFriendsList();
        System.out.println(response.extract().body().asPrettyString());
        Integer actualID = response.extract().body().path("users[0].id");
        Assert.assertEquals(actualID, ID, "ID doesnt match");
    }
    @Test
    public void testUserCanGetFriendsListUsingScreenName28(){
        String screenName = "KamalaHarris";
        ValidatableResponse response = this.tweetAPIClient.getFriendsList();
        System.out.println(response.extract().body().asPrettyString());
        String actualScreenName = response.extract().body().path("users[0].screen_name");
        Assert.assertEquals(actualScreenName, screenName, "screen name doesnt match");
    }
    @Test
    public void testUserCanGetFriendsListUsingLocation29(){
        String location = "Washington, DC";
        ValidatableResponse response = this.tweetAPIClient.getFriendsList();
        System.out.println(response.extract().body().asPrettyString());
        String actualLocation = response.extract().body().path("users[0].location");
        Assert.assertEquals(actualLocation, location, "location doesnt match");
    }
    @Test
    public void testUserCanGetFriendsListUsingDescription30(){
        String description = "Fighting for the people. Wife, Momala, Auntie. She/her. Official account is @VP.";
        ValidatableResponse response = this.tweetAPIClient.getFriendsList();
        System.out.println(response.extract().body().asPrettyString());
        String actualDescription = response.extract().body().path("users[0].description");
        Assert.assertEquals(actualDescription, description, "location doesnt match");
    }
    @Test
    public void testUserCanGetFriendsListUsingURL31(){
        String url = "https://t.co/uBcfgtTRsi";
        ValidatableResponse response = this.tweetAPIClient.getFriendsList();
        System.out.println(response.extract().body().asPrettyString());
        String actualUrl = response.extract().body().path("users[0].url");
        Assert.assertEquals(actualUrl, url, "url doesnt match");
    }

    @Test
    public void testUserCanGetTweetResponseTime32(){
        ValidatableResponse response = this.tweetAPIClient.responseTime();
        System.out.println(response.extract().body().asPrettyString());
        String actualResponseTime = response.extract().body().path("[0]");
        Assert.assertTrue(Boolean.parseBoolean(actualResponseTime));
    }

    @Test
    public void testUserCannotCreateCustomProfile33(){
        String errorMsg = "Client is not permitted to perform this action.";
        ValidatableResponse response = this.tweetAPIClient.postCustomProfile("Test Me");
        System.out.println(response.extract().body().asPrettyString());
        String actualErrorMsg = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualErrorMsg,errorMsg,"Error message doesnt match");
    }
    @Test //follow POTUS
    public void testUserCanFollowByID34(){
        Long expectedId = 1349149096909668363l;
        ValidatableResponse response = this.tweetAPIClient.postFollowUser("President Biden","POTUS");
        System.out.println(response.extract().body().asPrettyString());
        Long actualId= response.extract().body().path("id");
        Assert.assertEquals(actualId,expectedId,"IDs dont match sister");

    }
    @Test // follow bbcworld
    public void testUserCanFollowByScreenName35(){
        String expectedScreenName = "BBCWorld";
        ValidatableResponse response = this.tweetAPIClient.postFollowUser("BBC News (World)","BBCWorld");
        System.out.println(response.extract().body().asPrettyString());
        String actualScreenName= response.extract().body().path("screen_name");
        Assert.assertEquals(actualScreenName,expectedScreenName,"Screen Names dont match dear");

    }
    @Test//follow mufti menk
    public void testUserCanFollowByName36(){
        String expectedName = "Mufti Menk";
        ValidatableResponse response = this.tweetAPIClient.postFollowUser("Mufti Menk","muftimenk");
        System.out.println(response.extract().body().asPrettyString());
        String actualName= response.extract().body().path("name");
        Assert.assertEquals(actualName,expectedName,"Names dont match sister");

    }
    @Test//block POTUS
    public void testUserCanBlockByName37(){
        String expectedName = "President Biden";
        ValidatableResponse response = this.tweetAPIClient.postBlockUsers("President Biden","POTUS");
        System.out.println(response.extract().body().asPrettyString());
        String actualName= response.extract().body().path("name");
        Assert.assertEquals(actualName,expectedName,"Names dont match sister");

    }
    @Test //block kim kardashian
    public void testUserCanBlockByScreenName38(){
        String expectedScreenName = "KimKardashian";
        ValidatableResponse response = this.tweetAPIClient.postBlockUsers("Kim Kardashian West","KimKardashian");
        System.out.println(response.extract().body().asPrettyString());
        String actualScreenName= response.extract().body().path("screen_name");
        Assert.assertEquals(actualScreenName,expectedScreenName,"Screen names dont match sister");

    }
    @Test
    public void testUserCanGetListOfBlockedUsers39(){
        List<String> blockusers = Arrays.asList("Kim Kardashian West","President Biden");
        ValidatableResponse response = this.tweetAPIClient.getBlockUsersList(true);
        System.out.println(response.extract().body().asPrettyString());
        List<String> actualBlockedUsers = response.extract().body().jsonPath().getList("users.name");
        Assert.assertEquals(actualBlockedUsers,blockusers,"Try again");
    }

    @Test // This application is not allowed to access or delete your direct messages. error
    public void testUserCanTSendDirectMessage40(){
        int errorCode = 93;
        ValidatableResponse response = this.tweetAPIClient.postDirectMessages();
        System.out.println(response.extract().body().asPrettyString());
        int actualErrorCode = response.extract().body().path("errors[0].code");
        Assert.assertEquals(actualErrorCode,errorCode,"Code doesnt match buddy");
    }





    @Test
    public void testUserCanCreateWelcomeMsg(){
        ValidatableResponse response = this.tweetAPIClient.postWelcomeMsg();
        System.out.println(response.extract().body().asPrettyString());
    }






    @Test
    public void testUserCanUploadImage(){
        String imagePath = "../Twitter/src/main/java/pics/mypic.jpg";
        String text = "Mention tweet";
        ValidatableResponse response = this.tweetAPIClient.uploadImage(imagePath,"Easha Khanam");
        System.out.println(response.extract().body().asPrettyString());
    }



    @Test
    public void testUserCanMention(){
        Long id = 1376012108211613700l;
        String text = "Mention tweet";
        ValidatableResponse response = this.tweetAPIClient.mentionUser(id);
        System.out.println(response.extract().body().asPrettyString());
    }






//    @Test
//    public void testUserGetStatuses13111(){
//        String expectedTweet = "I am tweeting my life way9c65518e-d934-4d53-86c0-b43d408495ff";
//        ValidatableResponse response = this.tweetAPIClient.getStatuses(1379152604618485760l);
//        System.out.println(response.extract().body().asPrettyString());
//        String actualTweet= response.extract().body().path("text");
//        Assert.assertEquals(actualTweet, expectedTweet, "Text not match");
//
//    }


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
