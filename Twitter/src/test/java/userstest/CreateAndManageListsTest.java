package userstest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import users.CreateAndManageLists;


public class CreateAndManageListsTest {
    private CreateAndManageLists createAndManageLists;


    @BeforeClass
    public void setUpTweetAPI() {
        this.createAndManageLists = new CreateAndManageLists();
    }

    @Test //1
    public void testUserCanCreateListsWithAName() {
        String listName = "Oh! look what it is, its a list";
        ValidatableResponse response = this.createAndManageLists.createList(listName, "public", "Bootcamp bootcamp go away");
//        System.out.println(response.extract().body().asPrettyString());
        String actualListName = response.extract().body().path("name");
        Assert.assertEquals(actualListName, listName, "The name is not a match");
    }

    @Test //2
    public void testUserCanCreateListsWithAMode() {
        String listMode = "public";
        ValidatableResponse response = this.createAndManageLists.createList("Oh! look what it is, its a list", listMode, "Bootcamp bootcamp go away");
        //System.out.println(response.extract().body().asPrettyString());
        String actualListMode = response.extract().body().path("mode");
        Assert.assertEquals(actualListMode, listMode, "The list is private");
    }

    @Test //3
    public void testUserCanCreateListsWithADescription() {
        String listDescription = "Bootcamp bootcamp go away";
        ValidatableResponse response = this.createAndManageLists.createList("Oh! look what it is, its a list", "public", listDescription);
        //System.out.println(response.extract().body().asPrettyString());
        String actualListMode = response.extract().body().path("description");
        Assert.assertEquals(actualListMode, listDescription, "The list does not have a description");
    }

    @Test //4
    public void testUserCanGetListThatWasCreated() {
        String listName = "Oh! look what it is, its a list";
        ValidatableResponse response = this.createAndManageLists.getList("IsratReto");
        //System.out.println(response.extract().body().asPrettyString());
        String actualListName = response.extract().body().path("[0].name");
        Assert.assertEquals(actualListName, listName, "The name is not a match");
    }

    @Test //5
    public void testUserCanAddMembersToTheList() {
        String slug = "oh-look-what-it-is-its-a-list-12846";
        int expectedMemberCount = 2;
        ValidatableResponse response = this.createAndManageLists.addAMemberToTheList(slug, "Kaur0052", "IsratReto");
        //System.out.println(response.extract().body().asPrettyString());
        int actualMemberCount = response.extract().body().path("member_count");
        Assert.assertEquals(actualMemberCount, expectedMemberCount, "No member was added");
    }

    @Test //6
    public void testUserCanRetrieveMemberInformationFromList() {
        String addedUserScreenName = "Kaur0052";
        ValidatableResponse response = this.createAndManageLists.retrieveMemberFromTheList(1379849975195168778L);
        //System.out.println(response.extract().body().asPrettyString());
        String actualAddedUserScreenName = response.extract().body().path("users[0].screen_name");
        Assert.assertEquals(actualAddedUserScreenName, addedUserScreenName, "User does not exist");
    }

//    @Test //7
//    public void testUserCanDeleteMemberFromList(){
//        String addedUserScreenName = "Kaur0052";
//        ValidatableResponse response = this.createAndManageLists.deleteMember(1379849975195168778L,"Kaur0052");
//        System.out.println(response.extract().body().asPrettyString());
////        String actualAddedUserScreenName = response.extract().body().path("users[0].screen_name");
////        Assert.assertEquals(actualAddedUserScreenName,addedUserScreenName, "User does not exist");
////
//    }


}
