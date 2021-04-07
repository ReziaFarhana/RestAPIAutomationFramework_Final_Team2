package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.*;
import tweet.GetTweetTimelines;

import java.util.Arrays;
import java.util.List;

public class GetTweetTimelinesTest {
    private GetTweetTimelines tweetTimelines;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetTimelines = new GetTweetTimelines();
    }

    @Test //1
    public void testUserCanRetrieveTweetsNReTweetsFromHomeTimeline() {
        List<Integer> expectedStatusCount = Arrays.asList(11, 19, 13960, 19, 3522, 18, 11, 11, 11, 19, 19, 19, 19, 11, 11, 11, 3522, 3522);
        ValidatableResponse response = this.tweetTimelines.retrieveTweetsNReTweetsFromHomeTimeline("Israt Reto");
        //   System.out.println(response.extract().body().asPrettyString());
        List<Integer> actualStatusCount = response.extract().body().jsonPath().getList("user.statuses_count");
        //System.out.println(actualStatusCount);
        Assert.assertEquals(actualStatusCount, expectedStatusCount, "Status count is wrong");
    }

//    @Test //1
//    public void testUserCanRetrieveTweetsNReTweetsFromHomeTimeline() {
//        List<Integer> expectedStatusCount = Arrays.asList(11, 19, 13960, 19, 3522, 18, 11, 11, 11, 19, 19, 19, 19, 11, 11, 11, 3522, 3522);
//        ValidatableResponse response = this.tweetTimelines.retrieveTweetsNReTweetsFromHomeTimeline("Israt Reto");
//        //   System.out.println(response.extract().body().asPrettyString());
//        List<Integer> actualStatusCount = response.extract().body().jsonPath().getList("user.name");
//        System.out.println(actualStatusCount);
//        Assert.assertEquals(actualStatusCount, expectedStatusCount, "Status count is wrong");
//    }

    @Test //2
    public void testUserCanRetrieveMentionsFromTimeline() {
        List<String> expectedMentionScreenName = Arrays.asList("IsratReto");
        ValidatableResponse response = this.tweetTimelines.retrieveMentionsFromTimeline("Israt Reto");
        //  System.out.println(response.extract().body().asPrettyString());
        List<String> actualUserMentionScreenName = response.extract().body().path("entities.user_mentions[0].screen_name");
        //System.out.println(actualUserMentionScreenName);
        Assert.assertEquals(actualUserMentionScreenName, expectedMentionScreenName, "Wrong name");
    }

    @Test //3
    public void testUserCanRetrieveRecentTweetFromUserTimeline(){
        List<Long> expectedRecentTweetID = Arrays.asList(1379541712280895500L);
        ValidatableResponse response = this.tweetTimelines.retrieveRecentTweetFromUserTimeline("Israt Reto");
          System.out.println(response.extract().body().asPrettyString());
          List<Long> actualRecentTweetID = response.extract().body().jsonPath().getList("screen_name");
          Assert.assertEquals(actualRecentTweetID,expectedRecentTweetID, "Wrong tweet is displayed");

//    String expectedText = "another mention tweet @eashaarap";
//        ValidatableResponse response = this.tweetTimelines.retrieveRecentTweetFromUserTimeline(1379569473137557506L);
//          System.out.println(response.extract().body().asPrettyString());
//          String actualText = response.extract().body().path("text");
//          Assert.assertEquals(actualText,expectedText);

    }
}
