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
        //System.out.println(response.extract().body().asPrettyString());
        String actualScreenName = response.extract().body().path("screen_name");
        Assert.assertEquals(actualScreenName, expectedScreenName, "Wrong screen name");
    }

    @Test //2
    public void testUserCanVerifyCredential(){
        String expectedName = "Israt Reto";
        ValidatableResponse response = this.accountSettingsAndProfile.verifyCredential("Israt Reto",true);
        //System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("name");
        Assert.assertEquals(actualName, expectedName, "Wrong name");
    }

    @Test //3
    public void testUserCannotVerifyProfileBanner(){
        String expectedErrorMessage = "Sorry, that page does not exist";
        ValidatableResponse response = this.accountSettingsAndProfile.verifyProfileBanner("IsratReto");
        System.out.println(response.extract().body().asPrettyString());
        String actualErrorMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "No error message");
    }

    @Test //3
    public void testUserCanVerifyProfileBanner(){
        int heightOfProfilePictureOfWeb = 260;
        ValidatableResponse response = this.accountSettingsAndProfile.verifyProfileBanner("IsratReto");
//        System.out.println(response.extract().body().asPrettyString());
        int actualHeightOfProfilePictureOfWeb = response.extract().body().path("sizes.web.h");
        Assert.assertEquals(actualHeightOfProfilePictureOfWeb, heightOfProfilePictureOfWeb, "Wrong height");
    }

    @Test //4
    public void testUserCanremoveProfileBanner(){
        ValidatableResponse response = this.accountSettingsAndProfile.removeProfileBanner();
        System.out.println(response.extract().body().asPrettyString());

    }












}
