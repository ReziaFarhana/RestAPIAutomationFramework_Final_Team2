package userstest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import users.MuteBlockAndReportUsers;

import java.util.Arrays;
import java.util.List;

public class MuteBlockAndReportUsersTest {
    private MuteBlockAndReportUsers muteBlockAndReportUsers;


    @BeforeClass
    public void setUpTweetAPI() {
        this.muteBlockAndReportUsers = new MuteBlockAndReportUsers();
    }

    @Test //1
    public void testUserCanBlockSpeicficUser() {
        String expectedBlockedUserName = "Plaid Sheikh Hasina";
        ValidatableResponse response = this.muteBlockAndReportUsers.blockAUser("Plaid_Hasina");
        System.out.println(response.extract().body().asPrettyString());
        String actualBlockedUserName = response.extract().body().path("name");
        Assert.assertEquals(actualBlockedUserName, expectedBlockedUserName, "User has already been blocked");
    }

    @Test //2
    public void testUserCanGetAListOfBlockedUser() {
        List<String> blockedUsers = Arrays.asList("Plaid Sheikh Hasina", "Kim Kardashian West");
        ValidatableResponse response = this.muteBlockAndReportUsers.retrieveBlockedUsers(true);
        System.out.println(response.extract().body().asPrettyString());
        List<String> actualBlockedUsers = response.extract().body().jsonPath().getList("users.name");
        Assert.assertEquals(actualBlockedUsers, blockedUsers, "List is wrong");
    }

    @Test //3
    public void testUserCanUnblockABlockedUser(){
        String expectedBlockedUserName = "Plaid Sheikh Hasina";
        ValidatableResponse response = this.muteBlockAndReportUsers.unblockABlockedUsers("Plaid_Hasina",true);
        System.out.println(response.extract().body().asPrettyString());
        String actualBlockedUserName = response.extract().body().path("name");
        Assert.assertEquals(actualBlockedUserName, expectedBlockedUserName, "User has already been unblocked");
    }

}
