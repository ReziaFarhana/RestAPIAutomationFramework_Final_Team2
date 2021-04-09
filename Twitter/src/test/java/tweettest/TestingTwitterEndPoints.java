package tweettest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tweet.BaseAPIClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestingTwitterEndPoints {
    private BaseAPIClient base;

    @BeforeMethod
    public void setUp() {
        this.base = new BaseAPIClient();
    }

    public int getrandom() {
        int num;
        Random random = new Random(10);
        return num = (int) (Math.random() * 100);
    }

    @Test //01 sending a tweet
    public void verifyUserTweeted() {
        String tweet = "I hope I will be able to read my tweet  " + getrandom();
        ValidatableResponse response = base.userCanTweet(tweet);
        response.statusCode(200);
        String actual = response.extract().body().path("text");
        Assert.assertEquals(actual, tweet, "Tweet message doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test  //02 verification by tweet ID
    public void verifyReadTweetWithID() {
        String expectedTweet = "We are learning Rest API using Rest Assured and our First Tweet Same Tweet";
        ValidatableResponse response = base.readASingleTweetID(1378414522663337985L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, expectedTweet, "Test failed, tweet info doesn't match");
    }

    @Test  //03 verification by tweet name
    public void verifyReadTweetWithName() {
        String expectedTweet = "Sewunet Melkamu";
        ValidatableResponse response = base.readASingleTweetID(1378414522663337985L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("user.name");
        Assert.assertEquals(actualTweet, expectedTweet, "Test failed, sender name doesn't match");
    }

    @Test  //04 verification by tweet Screen Name
    public void verifyReadTweetWithScreenName() {
        String expectedTweet = "SewunetMelkamu";
        ValidatableResponse response = base.readASingleTweetID(1378414522663337985L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("user.screen_name");
        Assert.assertEquals(actualTweet, expectedTweet, "Test failed, sender name doesn't match");
    }

    @Test  //05 verification by tweet description
    public void verifyReadTweetWithDescription() {
        String expectedTweet = "እረ ተው እስቲ ሰው እንሁን";
        ValidatableResponse response = base.readASingleTweetID(1378414522663337985L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("user.description");
        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expectedTweet, "Test failed, sender name doesn't match");
    }

    @Test  //06 verification by tweet user id
    public void verifyReadTweetWithUserID() {
        String expectedTweet = "1376052489028521991";
        ValidatableResponse response = base.readASingleTweetID(1378414522663337985L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("user.id").toString();
        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expectedTweet, "Test failed, sender name doesn't match");
    }

    @Test  //07 Delete a tweet
    public void verifyDeleteATweet() {
//        String expected = "I hope I will be able to read my tweet  18";
        ValidatableResponse response = base.deleteATweet(1380554955011063818L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        System.out.println(response.extract().body().asPrettyString());
//        Assert.assertEquals(actualTweet, expected, "Test failed, sender name doesn't match");
    }

    @Test  //08 Verify to re-delete a tweet
    public void verifyReDeleteATweet() {
        String expected = "No status found with that ID.";
        ValidatableResponse response = base.deleteATweet(1378342316965031940L);
        response.statusCode(404);
        String actualTweet = response.extract().body().path("errors[0].message");
//        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expected, "Test failed, sender name doesn't match");
    }

    @Test  //09 Verify to retweet with a text
    public void verifyReTweetATweeWithText() {
        String expected = "RT @SewunetMelkamu: I hope I will be able to read my tweet  52";
        ValidatableResponse response = base.reTweetATweet(1379809746874695685L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
//        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expected, "Test failed, sender name doesn't match");
    }

    @Test  //10 Verify to retweet with entities
    public void verifyReTweetATweetEntities() {
        String expected = "Sewunet Melkamu";
        ValidatableResponse response = base.reTweetATweet(1378218145727578113L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("entities.user_mentions[0].name");
//        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expected, "Test failed, sender name doesn't match");
    }

    @Test  //11 Verify to retweet with retweeted=true
    public void verifyReTweetATweetIsTrue() {
        ValidatableResponse response = base.reTweetATweet(1378218145727578113L);
        response.statusCode(200);
        boolean actualTweet = response.extract().body().path("retweeted");
        Assert.assertEquals(actualTweet, true, "Test failed, sender name doesn't match");
    }

    @Test  //12 Verify to retweet with retweeted_status
    public void verifyReTweetATweetWithRetweeted_status() {
        String expected = "Looking forward to see my tweeting4125";
        ValidatableResponse response = base.reTweetATweet(1378218145727578113L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("retweeted_status.text");
//        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expected, "Test failed, sender name doesn't match");
    }

    @Test  //13 Verify a re-tweet is  un-retweeted
    public void verifyUnReTweetATweet() {
        String expected = "Looking forward to see my tweeting4125";
        ValidatableResponse response = base.unReTweetATweet(1378218145727578113L);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        System.out.println(response.extract().body().asPrettyString());
        Assert.assertEquals(actualTweet, expected, "Test failed, sender name doesn't match");
    }

    @Test //14 Tweeting identical tweets in a row will produce an error
    public void verifyDuplicateTweetInRowErrors() {
        String tweet = "Looking forward to see my tweeting4125";
        ValidatableResponse response = base.userCanTweet(tweet);
        response.statusCode(403);
        String expectedErrore = "Status is a duplicate.";
        String actual = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actual, expectedErrore, "Tweet message doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test //15 like a tweet
    public void verifyATweetIsLiked() {
        ValidatableResponse response = base.favoriteATweet(1378218145727578113L);
        response.statusCode(200);
        boolean actual = response.extract().body().path("favorited");
        Assert.assertEquals(actual, true, "Tweet message doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test //16 change liked tweet
    public void verifyATweetIsUnLiked() {
        ValidatableResponse response = base.unfavoriteATweet(1378218145727578113L);
        response.statusCode(200);
        boolean actual = response.extract().body().path("favorited");
        Assert.assertEquals(actual, false, "Tweet message doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test  // 17 create a default list - expect a name
    public void createList() {
        String name = "To keep all in ONE";
        ValidatableResponse response = base.createList(name);
        response.statusCode(200);
        String actual = response.extract().body().path("name");
        Assert.assertEquals(actual, name, "Test failed - List name doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test  // 18 create a private list - expect a name
    public void createListWithAccess() {
        String name = "To keep all in ONE";
        String mode = "private";
        ValidatableResponse response = base.createListWithAccess(name, mode);
        response.statusCode(200);
        String actual = response.extract().body().path("mode");
        Assert.assertEquals(actual, mode, "Test failed - access specifier doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test  // 19 create a private list - expect a name
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

    @Test  // 20 to get all the available list created
    public void getList() {
        ValidatableResponse response = base.getAllists("SewunetMelkamu");
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test  // 21 delete a list using the list ID
    public void deleteWithListID() {
        String expected = "To keep all in ONE";
        ValidatableResponse response = base.deleteListID(1379675109338800129L);
        response.statusCode(200);
        String actual = response.extract().body().path("name");
        Assert.assertEquals(actual, expected, "Test failed - name doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test  // 22 delete a list using the slug
    public void deleteWithListSlug() {
        String slug = "to-keep-all-in-one-20293";
        ValidatableResponse response = base.deleteListWithSlug(slug, "SewunetMelkamu");
        response.statusCode(200);
        String actual = response.extract().body().path("slug");
        Assert.assertEquals(actual, slug, "Test failed - name doesn't match");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test // 23 trying to delete a deleted list using the list ID
    public void deleteADeletedListWithID() {
        String expected = "Sorry, that page does not exist.";
        ValidatableResponse response = base.deleteListID(1379675109338800129L);
        response.statusCode(404);
        String actual = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actual, expected, "Test failed ");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test  // 24 trying to delete a deleted list using the slug
    public void deleteADeletedListWithSlug() {
        String slug = "to-keep-all-in-one-17327";
        String expected = "Sorry, that page does not exist.";
        ValidatableResponse response = base.deleteListWithSlug(slug, "SewunetMelkamu");
        response.statusCode(404);
        String actual = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actual, expected, "Test failed");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test  // 25
    public void verifylistOfRetweetOfMe() {
        ValidatableResponse response = base.listOfRetweetOfMe();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
    }

    @Test  //  26 tweeters of retweet of me
    public void verifyListOfTweets() throws ParseException {
        ValidatableResponse response = base.listOfRetweetOfMe();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);
        // need more work
    }

    @Test  //27 Verify to retweet a tweet
    public void verifyFavoriteTweet() {
        ValidatableResponse response = base.getFavorite();
        response.statusCode(HttpStatus.SC_OK);
        System.out.println(response.extract().body().asPrettyString());

    }


}
