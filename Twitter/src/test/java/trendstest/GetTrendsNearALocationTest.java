package trendstest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import trends.GetTrendsNearALocation;

public class GetTrendsNearALocationTest {
    private GetTrendsNearALocation trendsNearALocation;

    @BeforeClass
    public void setUpTweetAPI() {
        this.trendsNearALocation = new GetTrendsNearALocation();
    }

   @Test //1
   public void testUserCanGetTrends(){
        String locationName = "Worldwide";
       ValidatableResponse response = this.trendsNearALocation.getTrends(1);
       System.out.println(response.extract().body().asPrettyString());
       String actualLocationName = response.extract().body().path("[0].locations[0].name");
       Assert.assertEquals(actualLocationName,locationName,"Location is not a match");
   }






















































}
