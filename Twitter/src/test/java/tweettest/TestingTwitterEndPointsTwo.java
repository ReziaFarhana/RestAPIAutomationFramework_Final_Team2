package tweettest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tweet.BaseAPIClient;
import tweet.BaseAPIClientTwo;

import java.util.List;

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
        ValidatableResponse response = base.createABlockAUser(screen_name);
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
        ValidatableResponse response = base.unBlockABloackedUser(screen_name);
        response.statusCode(200);
        String actual = response.extract().body().path("status.source");
        Assert.assertEquals(actual, expected, "Test failed");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test // 3 Verify a user is blocked
    public void verifyBlockedUser() throws ParseException {
        ValidatableResponse response = base.getBlockedUser();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actual = response.extract().path("next_cursor_str");
        Assert.assertEquals(actual,"0","Test failed");
    }

    @Test  // 4 GET /2/tweets
    public void verifygetTweetWith_ids() {
        String expected = "I hope I will be able to read my tweet  52";
        ValidatableResponse response = base.getTweet("1379809746874695685");
        System.out.println(response.extract().body().asPrettyString());
        String actual = response.extract().body().path("data[0].text");
        Assert.assertEquals(actual, expected, "Test failed");
    }

    @Test // 5 create a collection
    public void createCollections(){
        String name = "My First Collection";
        ValidatableResponse response = base.createCollections(name);
        response.statusCode(200);
        System.out.println(response.extract().asPrettyString());
        String expected ="custom-*";
        String actual = response.extract().path("response.timeline_id");
        Assert.assertNotEquals(actual,expected,"Test failed");
    }

    @Test  // 6 get a list of collections
    public void getCollectionLists(){
        String screen_name = "SewunetMelkamu";
        ValidatableResponse response = base.getCollectionsLists(screen_name);
        response.statusCode(200);
        System.out.println(response.extract().asPrettyString());
        String value = response.extract().path("response.cursors.next_cursor");
        boolean actual =  value != null;
        Assert.assertEquals(actual,true,"Test Failed");
    }

    @Test  // 7  Remove a collection
    public void verifyACollectionIsRemoved(){
        ValidatableResponse response = base.removeCollectionsLists("custom-1379938273850761219","1376052489028521991");
//        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("destroyed");
        Assert.assertEquals(actual,true,"Test failed");
    }

    @Test // 8 find followers
    public void verifyFollowers(){
        ValidatableResponse response = base.findFollowers();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("users[0].following");
        Assert.assertEquals(actual,true,"Test failed");
    }

    @Test  // 9 get account information
    public void getInfoOfAccount(){
        ValidatableResponse response = base.getAccountSetting();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("always_use_https");
        Assert.assertEquals(actual,true,"Test failed");
    }

    @Test // 10 unfollow
    public void verifyUnfollowFollower(){
        String screen_name = "Habeshantweets";
        ValidatableResponse response = base.unfollowIndividualFollower(screen_name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("following");
        System.out.println(actual);
        Assert.assertNotEquals(actual,true,"Test failed");
    }

    @Test  // 11 create friendship
    public void createFriendships(){
        String screen_name = "Habeshantweets";
        ValidatableResponse response = base.createFriendships(screen_name,true);
        response.statusCode(200);
//        System.out.println(response.extract().body().asPrettyString());
        boolean following = response.extract().path("following");
        Assert.assertEquals(following,true,"Test failed");
    }

    @Test  // 12 update profile
    public void updateProfile(){
        String screen_name = "SewunetMelkamu";
        String location = "Virginia for Lovers";
        ValidatableResponse response = base.updateProfile(screen_name,"http://selam-lehulum.com");
        System.out.println(response.extract().body().asPrettyString());
        String actual = response.extract().body().path("location");
        Assert.assertEquals(actual,location,"Test failed");
    }

    @Test  // 13 mute a user
    public void muteUser(){
        String screen_name = "FLOTUS";
        ValidatableResponse response = base.muteUser(screen_name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("muting");
        Assert.assertEquals(actual,true,"Test failed");
    }

     @Test  // 14 mute a user
    public void UnMuteUser(){
        String screen_name = "FLOTUS";
        ValidatableResponse response = base.unMuteUser(screen_name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        boolean actual = response.extract().body().path("muting");
        Assert.assertEquals(actual,false,"Test failed");
    }














}
