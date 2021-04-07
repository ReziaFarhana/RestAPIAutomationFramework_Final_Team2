package userstest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import users.ManageAccountSettingsAndProfile;

import java.util.Arrays;
import java.util.List;

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
    public void testUserCanVerifyCredential() {
        String expectedName = "Israt Reto";
        ValidatableResponse response = this.accountSettingsAndProfile.verifyCredential("Israt Reto", true);
        //System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("name");
        Assert.assertEquals(actualName, expectedName, "Wrong name");
    }

    @Test //3
    public void testUserCannotVerifyProfileBanner() {
        String expectedErrorMessage = "Sorry, that page does not exist";
        ValidatableResponse response = this.accountSettingsAndProfile.verifyProfileBanner("IsratReto");
        System.out.println(response.extract().body().asPrettyString());
        String actualErrorMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "No error message");
    }

    @Test //4
    public void testUserCanVerifyProfileBanner() {
        int heightOfProfilePictureOfWeb = 260;
        ValidatableResponse response = this.accountSettingsAndProfile.verifyProfileBanner("IsratReto");
//        System.out.println(response.extract().body().asPrettyString());
        int actualHeightOfProfilePictureOfWeb = response.extract().body().path("sizes.web.h");
        Assert.assertEquals(actualHeightOfProfilePictureOfWeb, heightOfProfilePictureOfWeb, "Wrong height");
    }

    @Test //5
    public void testUserCanremoveProfileBanner() {
        ValidatableResponse response = this.accountSettingsAndProfile.removeProfileBanner();
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test //6
    public void testUserCanUpdateUserTimeZoneSettings() {
        String expectedTimeZone = "Central Time (US & Canada)";
        ValidatableResponse response = this.accountSettingsAndProfile.updatesUserSettings("en-gb", "Central Time (US & Canada)");
        //System.out.println(response.extract().body().asPrettyString());
        String actualTimeZone = response.extract().body().path("time_zone.name");
        Assert.assertEquals(actualTimeZone, expectedTimeZone, "Wrong time zone is displayed");

    }

    @Test //7
    public void testUserCanUpdateUserLanguageSettings() {
        String expectedLanguage = "en-gb";
        ValidatableResponse response = this.accountSettingsAndProfile.updatesUserSettings("en-gb", "Central Time (US & Canada)");
        //System.out.println(response.extract().body().asPrettyString());
        String actualLanguage = response.extract().body().path("language");
        Assert.assertEquals(actualLanguage, expectedLanguage, "Wrong language displayed");
    }

    @Test //8
    public void testUserCanUpdateProfileLocation() {
        String expectedLocation = "San Francisco CA";
        ValidatableResponse response = this.accountSettingsAndProfile.updateProfile("San Francisco CA", "http://freshFromBD.bd", "I eat sleep PNT");
        //System.out.println(response.extract().body().asPrettyString());
        String actualLocation = response.extract().body().path("location");
        Assert.assertEquals(actualLocation, expectedLocation, "Wrong location");
    }

    @Test //9
    public void testUserCanUpdateProfileExpandedUrl() {
        String expectedUrl = "http://freshFromBD.bd";
        ValidatableResponse response = this.accountSettingsAndProfile.updateProfile("San Francisco CA", "http://freshFromBD.bd", "I eat sleep PNT");
        //System.out.println(response.extract().body().asPrettyString());
        String actualUrl = response.extract().body().path("entities.url.urls[0].expanded_url");
        Assert.assertEquals(actualUrl, expectedUrl, "Url does not exist");
    }

    @Test //10
    public void testUserCanUpdateProfileExpandedDescription() {
        String expectedDescription = "I eat sleep PNT";
        ValidatableResponse response = this.accountSettingsAndProfile.updateProfile("San Francisco CA", "http://freshFromBD.bd", "I eat sleep PNT");
        //System.out.println(response.extract().body().asPrettyString());
        String actualDescription = response.extract().body().path("description");
        Assert.assertEquals(actualDescription, expectedDescription, "Empty description");
    }

    @Test //11
    public void testUserCanSeeSavedSearchList() {
        List<String> queryList = Arrays.asList("energy source", "mars rover", "Hijab ban france");
        ValidatableResponse response = this.accountSettingsAndProfile.returnSavedSearchList();
        //System.out.println(response.extract().body().asPrettyString());
        List<String> actualQueryList = response.extract().body().jsonPath().getList("query");
        // System.out.println(actualQueryList);
        Assert.assertEquals(actualQueryList, queryList, "Empty list");
    }

    @Test //12
    public void testUserCannotRetrieveInformationOnASavedSearch() {
        String expectedErrorMessage = "Sorry, that page does not exist";
        ValidatableResponse response = this.accountSettingsAndProfile.showSavedSearches(1378039805418872832L);
        //System.out.println(response.extract().body().asPrettyString());
        String actualErrorMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "No error message");
    }

    @Test//13
    public void testUserCanCreateASavedSearch() {
        String expectedQuery = "Manual Testing";
        ValidatableResponse response = this.accountSettingsAndProfile.createASavedSearch("Manual Testing");
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("query");
        Assert.assertEquals(actualQuery, expectedQuery, "Query was not added");
    }

    @Test //14
    public void testUserCanDeleteSavedSearchList() {
        List<String> queryList = Arrays.asList("energy source", "mars rover", "Hijab ban france", "Gorilla", "Automation Testing");
        ValidatableResponse response = this.accountSettingsAndProfile.deleteASavedSearch(1379800176546754561L);
//        System.out.println(response.extract().body().asPrettyString());
        List<String> actualQueryList = response.extract().body().jsonPath().getList("query");
//        System.out.println(actualQueryList);
        Assert.assertEquals(actualQueryList, queryList, "Empty list");
    }





}
