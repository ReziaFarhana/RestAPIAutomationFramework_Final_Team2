package tweettest;

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

    @BeforeMethod
    public void setUp() {
        this.base = new BaseAPIClientTwo();
    }

    @Test
    public void verifyABlockIsSet(){
        String screen_name = "united";
        ValidatableResponse response = base.createABlockAUser(screen_name);
        response.statusCode(200);
        String actual = response.extract().body().path("screen_name");
        Assert.assertEquals(actual,screen_name,"Test failed");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void verifyUnBlockABlockedUser(){
        String screen_name = "united";
        ValidatableResponse response = base.unBlockABloackedUser(screen_name);
        response.statusCode(200);
        String actual = response.extract().body().path("screen_name");
        Assert.assertEquals(actual,screen_name,"Test failed");
        System.out.println(response.extract().body().asPrettyString());
    }



    @Test
    public void verifyBlockedUser() throws ParseException {
        ValidatableResponse response = base.getBlockedUser();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
    }

     @Test
    public void verifyBlockedUserWithID() throws ParseException {
        ValidatableResponse response = base.getBlockedUserWithID(260907612L);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
    }




}
