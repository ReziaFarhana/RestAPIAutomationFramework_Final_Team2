package tweettest;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tweet.TweetAPIClient;

import java.util.Random;

public class TweetAPIClientTest {
    private TweetAPIClient base;
    public static ValidatableResponse response;


    @BeforeMethod
    public void setUp() {
        this.base = new TweetAPIClient();
    }@

    public int getrandom() {
        int num;
        Random random = new Random(10);
        return num = (int) (Math.random() * 100);
    }

    @Test
    public void verifyUserTweeted() {
        String tweet = "I will be able to read my tweet  " + getrandom();
        ValidatableResponse response = base.userCanTweet(tweet);
        response.statusCode(200);
        String actual = response.extract().body().path("text");
        Assert.assertEquals(actual, tweet, "Tweet message doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void verifyReadTweetWithID() {
        String expectedTweet = "We are learning Rest API using Rest Assured and our First Tweet Same Tweet";
        ValidatableResponse response = base.readASingleTweetID(1378414522663337985L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, expectedTweet, "Test failed, tweet info doesn't match");
    }

    @Test
    public void verifyReadTweetWithName() {
        String expectedTweet = "Nafiz";
        ValidatableResponse response = base.readASingleTweetID(1378414522663337985L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("user.name");
        Assert.assertEquals(actualTweet, expectedTweet, "Test failed, sender name doesn't match");
    }

    @Test
    public void verifyReadTweetWithScreenName() {
        String expectedTweet = "Nafiz";
        ValidatableResponse response = base.readASingleTweetID(1378414522663337985L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("user.screen_name");
        Assert.assertEquals(actualTweet, expectedTweet, "Test failed, sender name doesn't match");
    }

    @Test
    public void verifyReadTweetWithDescription() {
        String expectedTweet = "I expected this";
        ValidatableResponse response = base.readASingleTweetID(1378414522663337985L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("user.description");
        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expectedTweet, "Test failed, sender name doesn't match");
    }

    @Test
    public void verifyReadTweetWithUserID() {
        String expectedTweet = "1376052489028521991";
        ValidatableResponse response = base.readASingleTweetID(1378414522663337985L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("user.id").toString();
        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expectedTweet, "Test failed, sender name doesn't match");
    }

    @Test
    public void verifyDeleteATweet() {
        String expected = "I will be able to delete my tweet 5";
        ValidatableResponse response = base.deleteATweet(1380554955011063818L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expected, "Test failed, sender name doesn't match");
    }

    @Test
    public void verifyReDeleteATweet() {
        String expected = "No status found with that ID.";
        ValidatableResponse response = base.deleteATweet(1378342316965031940L);
        response.statusCode(404);
        String actualTweet = response.extract().body().path("errors[0].message");
//        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expected, "Test failed, sender name doesn't match");
    }

    @Test
    public void verifyReTweetATweeWithText() {
        String expected = "RT @Nafiz:  I will be able to read my tweet  2";
        ValidatableResponse response = base.reTweetATweet(1379809746874695685L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
//        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expected, "Test failed, sender name doesn't match");
    }

    @Test
    public void verifyReTweetATweetEntities() {
        String expected = "Nafiz";
        ValidatableResponse response = base.reTweetATweet(1378218145727578113L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("entities.user_mentions[0].name");
//        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expected, "Test failed, sender name doesn't match");
    }

    @Test
    public void verifyReTweetATweetIsTrue() {
        ValidatableResponse response = base.reTweetATweet(1378218145727578113L);
        response.statusCode(200);
        boolean actualTweet = response.extract().body().path("retweeted");
        Assert.assertEquals(actualTweet, true, "Test failed, sender name doesn't match");
    }

    @Test
    public void verifyReTweetATweetWithRetweeted_status() {
        String expected = "Looking forward to see my tweeting4125";
        ValidatableResponse response = base.reTweetATweet(1378218145727578113L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("retweeted_status.text");
//        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expected, "Test failed, sender name doesn't match");
    }

    @Test
    public void verifyUnReTweetATweet() {
        String expected = "Looking forward to see my tweeting4125";
        ValidatableResponse response = base.unReTweetATweet(1378218145727578113L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expected, "Test failed, sender name doesn't match");
    }

    @Test
    public void verifyDuplicateTweetInRowErrors() {
        String tweet = "Looking forward to see my tweeting4125";
        ValidatableResponse response = base.userCanTweet(tweet);
        response.statusCode(403);
        String expectedErrore = "Status is a duplicate.";
        String actual = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actual, expectedErrore, "Tweet message doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void verifyATweetIsLiked() {
        ValidatableResponse response = base.favoriteATweet(1378218145727578113L);
        response.statusCode(200);
        boolean actual = response.extract().body().path("favorited");
        Assert.assertEquals(actual, true, "Tweet message doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void verifyATweetIsUnLiked() {
        ValidatableResponse response = base.unfavoriteATweet(1378218145727578113L);
        response.statusCode(200);
        boolean actual = response.extract().body().path("favorited");
        Assert.assertEquals(actual, false, "Tweet message doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void createList() {
        String name = "To keep all in ONE";
        ValidatableResponse response = base.createList(name);
        response.statusCode(200);
        String actual = response.extract().body().path("name");
        Assert.assertEquals(actual, name, "Test failed - List name doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void createListWithAccess() {
        String name = "To keep all in ONE";
        String mode = "private";
        ValidatableResponse response = base.createListWithAccess(name, mode);
        response.statusCode(200);
        String actual = response.extract().body().path("mode");
        Assert.assertEquals(actual, mode, "Test failed - access specifier doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void createListWithMultiParam() {
        String name = "To keep all in ONE";
        String mode = "private";
        String description = "This list is for Demo purpose";
        ValidatableResponse response = base.createListWithAccessAndDescription(name, mode, description);
        response.statusCode(200);
        String actual = response.extract().body().path("mode");
        Assert.assertEquals(actual, mode, "Test failed - access specifier doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void getList() {
        ValidatableResponse response = base.getAllLists("Nafiz");
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void deleteWithListID() {
        String expected = "To keep all in ONE";
        ValidatableResponse response = base.deleteListID(1379675109338800129L);
        response.statusCode(200);
        String actual = response.extract().body().path("name");
        Assert.assertEquals(actual, expected, "Test failed - name doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void deleteWithListSlug() {
        String slug = "to-keep-all-in-one-20293";
        ValidatableResponse response = base.deleteListWithSlug(slug, "Nafiz");
        response.statusCode(200);
        String actual = response.extract().body().path("slug");
        Assert.assertEquals(actual, slug, "Test failed - name doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void deleteADeletedListWithID() {
        String expected = "Sorry, that page does not exist.";
        ValidatableResponse response = base.deleteListID(1379675109338800129L);
        response.statusCode(404);
        String actual = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actual, expected, "Test failed ");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void deleteADeletedListWithSlug() {
        String slug = "to-keep-all-in-one-17327";
        String expected = "Sorry, that page does not exist.";
        ValidatableResponse response = base.deleteListWithSlug(slug, "Nafiz");
        response.statusCode(404);
        String actual = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actual, expected, "Test failed");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void verifylistOfRetweetOfMe() {
        ValidatableResponse response = base.listOfRetweetOfMe();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
    }

    @Test
    public void verifyListOfTweets() throws ParseException {
        ValidatableResponse response = base.listOfRetweetOfMe();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);
        // need more work
    }

    @Test
    public void verifyFavoriteTweet() {
        ValidatableResponse response = base.getFavorite();
        response.statusCode(HttpStatus.SC_OK);
        System.out.println(response.extract().body().asPrettyString());

    }

    @Test
    public void verifyABlockIsSet() {
        String screen_name = "united";
        String expected = "<a href=\"https://www.lithium.com\" rel=\"nofollow\">Khoros CX</a>";
        response = base.createABlockAUser(screen_name);
        response.statusCode(200);
        String actual = response.extract().body().path("status.source");
        Assert.assertEquals(actual, expected, "Test failed");
        System.out.println(response.extract().body().jsonPath().prettyPrint());
    }

    @Test
    public void verifyBlockedUser() throws ParseException {
        response = base.getBlockedUser();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actual = response.extract().path("next_cursor_str");
        Assert.assertEquals(actual, "0", "Test failed");
    }

    @Test
    public void verifyCreateCollections() {
        String name = "My First Collection";
        response = base.createCollections(name);
        response.statusCode(200);
        System.out.println(response.extract().asPrettyString());
        String expected = "custom-*";
        String actual = response.extract().path("response.timeline_id");
        Assert.assertNotEquals(actual, expected, "Test failed");
    }

    @Test
    public void verifyGetInfoOfAccount() {
        response = base.getAccountSetting();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("always_use_https");
        Assert.assertEquals(actual, true, "Test failed");

    }



}