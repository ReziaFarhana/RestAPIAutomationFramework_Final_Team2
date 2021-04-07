package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.FilterRealTimeTweets;

public class FilterRealTimeTweetsTest {
    private FilterRealTimeTweets realTimeTweets;

    @BeforeClass
    public void setUpTweetAPI() {
        this.realTimeTweets = new FilterRealTimeTweets();
    }


    @Test //1
    public void testUserCannotRetrieveStatusesThatMatchTheFilter(){
        String errorMessage = "Sorry, that page does not exist";
        ValidatableResponse response = this.realTimeTweets.statusesThatMatchTheFilter("twitter");
        //System.out.println(response.extract().body().asPrettyString());
        String actualErrorMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualErrorMessage, errorMessage, "Error message is the same");
    }


}
