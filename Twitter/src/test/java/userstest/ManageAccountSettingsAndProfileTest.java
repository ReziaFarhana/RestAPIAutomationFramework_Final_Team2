package userstest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import users.ManageAccountSettingsAndProfile;

public class ManageAccountSettingsAndProfileTest {
    private ManageAccountSettingsAndProfile accountSettingsAndProfile;


    @BeforeClass
    public void setUpTweetAPI() {
        this.accountSettingsAndProfile = new ManageAccountSettingsAndProfile();
    }

    @Test //1
    public void testUserCanGetUserSettings() {
        String expectedScreenName = "IsratReto";
        ValidatableResponse response = this.accountSettingsAndProfile.returnUserSettings();
        System.out.println(response.extract().body().asPrettyString());
        String actualScreenName = response.extract().body().path("screen_name");
        Assert.assertEquals(actualScreenName, expectedScreenName, "Wrong screen name");
    }




}
