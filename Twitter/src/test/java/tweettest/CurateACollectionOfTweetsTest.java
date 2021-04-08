package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.CurateACollectionOfTweets;

import java.util.LinkedHashMap;
import java.util.Map;

public class CurateACollectionOfTweetsTest {
    private CurateACollectionOfTweets collectionOfTweets;

    @BeforeClass
    public void setUpTweetAPI() {
        this.collectionOfTweets = new CurateACollectionOfTweets();
    }

    @Test //1
    public void testUserCanCreateACollection(){
        Map<String,String> collectionName = new LinkedHashMap<>();
        collectionName.put("name","Collecting to put in my collection");
        collectionName.put("user_id","1376012108211613700");
        collectionName.put("collection_url","https://twitter.com/IsratReto/timelines/1380163180303900683");
        collectionName.put("custom_timeline_url","https://twitter.com/IsratReto/timelines/1380163180303900683");
        collectionName.put("description","Hobby is to collect");
        collectionName.put("url","httpsbootcamppnt.com");
        collectionName.put("visibility","public");
        collectionName.put("timeline_order","curation_reverse_chron");
        collectionName.put("collection_type","user");
        collectionName.put("custom_timeline_type","user");

        ValidatableResponse response = this.collectionOfTweets.createCollections("Collecting to put in my collection","Hobby is to collect","httpsbootcamppnt.com");
        System.out.println(response.extract().body().asPrettyString());
        Map<String,String> actualCollectionName = response.extract().body().jsonPath().getMap("objects.timelines");
        System.out.println(actualCollectionName.get("custom"));
        Assert.assertEquals(actualCollectionName.get("name"),collectionName.get("name"),"Name is wrong");
    }
}
