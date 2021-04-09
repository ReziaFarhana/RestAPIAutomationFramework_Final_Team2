package tweettest;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tweet.BaseAPIClientTwo;
import payload.MyPayload;

import java.io.FileNotFoundException;

public class TestingTwitterEndPointsTwo {
    private BaseAPIClientTwo base;
    public static ValidatableResponse response;

    @BeforeMethod
    public void setUp() {
        this.base = new BaseAPIClientTwo();
    }


    @Test // 1 Block a user
    public void verifyABlockIsSet() {
        String screen_name = "united";
        String expected = "<a href=\"https://www.lithium.com\" rel=\"nofollow\">Khoros CX</a>";
        response = base.createABlockAUser(screen_name);
        response.statusCode(200);
        String actual = response.extract().body().path("status.source");
        Assert.assertEquals(actual, expected, "Test failed");
//        System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().jsonPath().prettyPrint());
    }

    @Test  // 2 unBlock a user
    public void verifyUnBlockABlockedUser() {
        String screen_name = "united";
        String expected = "<a href=\"https://www.lithium.com\" rel=\"nofollow\">Khoros CX</a>";
        response = base.unBlockABloackedUser(screen_name);
        response.statusCode(200);
        String actual = response.extract().body().path("status.source");
        Assert.assertEquals(actual, expected, "Test failed");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test // 3 Verify a user is blocked
    public void verifyBlockedUser() throws ParseException {
        response = base.getBlockedUser();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actual = response.extract().path("next_cursor_str");
        Assert.assertEquals(actual, "0", "Test failed");
    }

    @Test  // 4 GET /2/tweets
    public void verifygetTweetWith_ids() {
        String expected = "I hope I will be able to read my tweet  52";
        response = base.getTweet("1379809746874695685");
        System.out.println(response.extract().body().asPrettyString());
        String actual = response.extract().body().path("data[0].text");
        Assert.assertEquals(actual, expected, "Test failed");
    }

    @Test // 5 create a collection
    public void verifyCreateCollections() {
        String name = "My First Collection";
        response = base.createCollections(name);
        response.statusCode(200);
        System.out.println(response.extract().asPrettyString());
        String expected = "custom-*";
        String actual = response.extract().path("response.timeline_id");
        Assert.assertNotEquals(actual, expected, "Test failed");
    }

    @Test  // 6 get a list of collections
    public void verifyGetCollectionLists() {
        String screen_name = "SewunetMelkamu";
        response = base.getCollectionsLists(screen_name);
        response.statusCode(200);
        System.out.println(response.extract().asPrettyString());
        String value = response.extract().path("response.cursors.next_cursor");
        boolean actual = value != null;
        Assert.assertEquals(actual, true, "Test Failed");
    }

    @Test  // 7  Remove a collection
    public void verifyACollectionIsRemoved() {
        response = base.removeCollectionsLists("custom-1379938273850761219", "1376052489028521991");
//        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("destroyed");
        Assert.assertEquals(actual, true, "Test failed");
    }

    @Test // 8 find followers
    public void verifyFollowers() {
        response = base.findFollowers();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("users[0].following");
        Assert.assertEquals(actual, true, "Test failed");
    }

    @Test  // 9 get account information
    public void verifyGetInfoOfAccount() {
        response = base.getAccountSetting();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("always_use_https");
        Assert.assertEquals(actual, true, "Test failed");
    }

    @Test // 10 unfollow
    public void verifyUnfollowFollower() {
        String screen_name = "Habeshantweets";
        response = base.unfollowIndividualFollower(screen_name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("following");
        System.out.println(actual);
        Assert.assertNotEquals(actual, true, "Test failed");
    }

    @Test  // 11 create friendship
    public void verifyCreateFriendships() {
        String screen_name = "Habeshantweets";
        response = base.createFriendships(screen_name, true);
        response.statusCode(200);
//        System.out.println(response.extract().body().asPrettyString());
        boolean following = response.extract().path("following");
        Assert.assertEquals(following, true, "Test failed");
    }

    @Test  // 12 update profile
    public void verifyUpdateProfile() {
        String screen_name = "SewunetMelkamu";
        String location = "Virginia for Lovers";
        response = base.updateProfile(screen_name, "http://selam-lehulum.com");
        System.out.println(response.extract().body().asPrettyString());
        String actual = response.extract().body().path("location");
        Assert.assertEquals(actual, location, "Test failed");
    }

    @Test  // 13 mute a user
    public void verifyMuteUser() {
        String screen_name = "FLOTUS";
        response = base.muteUser(screen_name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("muting");
        Assert.assertEquals(actual, true, "Test failed");
    }

    @Test  // 14 Un mute a user
    public void verifyUnMuteUser() {
        String screen_name = "FLOTUS";
        response = base.unMuteUser(screen_name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("muting");
        Assert.assertEquals(actual, false, "Test failed");
    }

    @Test  // 15 upload image
    public void verifyUploadMedia() {
        response = base.mediaUpload(MyPayload.myHeart());
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
    }

    @Test  // 16 veryUploaded image is on the page
    public void verifyImageIsOnFront() {
        String hello = "Love is all what we have";
        response = base.tweetTheImage(hello,1380554541901479940L);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
    }

    @Test  // 17 send a message
    public void verifyCreateAMessage() throws FileNotFoundException {
        response = base.createAMessage();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);
    }

    @Test  // 18 send a message
    public void verifyCreateAMessageWithImage() throws FileNotFoundException {
        response = base.createAMessageWithImage();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);
    }










}
