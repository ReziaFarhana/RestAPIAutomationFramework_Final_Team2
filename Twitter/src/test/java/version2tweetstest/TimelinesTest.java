package version2tweetstest;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import version2tweets.Timelines;

public class TimelinesTest {
    private Timelines timelines;


    @BeforeClass
    public void setUpTweetAPI() {
        this.timelines = new Timelines();
    }

    @Test //1
    public void testUserCanUsersMention(){
        ValidatableResponse response = this.timelines.usersMention("1379569473137557506");
        System.out.println(response.extract().body().asPrettyString());
    }
}
