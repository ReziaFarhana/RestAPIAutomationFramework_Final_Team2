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
    public void testUserCanCreateListsWithAName(){
        String listName = "Oh! look what it is, its a list";
        ValidatableResponse response = this.createAndManageLists.createList(listName,"public","Bootcamp bootcamp go away");
        //System.out.println(response.extract().body().asPrettyString());
        String actualListName = response.extract().body().path("name");
        Assert.assertEquals(actualListName,listName,"The name is not a match");
    }

    @Test //2
    public void testUserCanCreateListsWithAMode(){
        String listMode = "public";
        ValidatableResponse response = this.createAndManageLists.createList("Oh! look what it is, its a list",listMode,"Bootcamp bootcamp go away");
        //System.out.println(response.extract().body().asPrettyString());
        String actualListMode = response.extract().body().path("mode");
        Assert.assertEquals(actualListMode,listMode,"The list is private");
    }

    @Test //3
    public void testUserCanCreateListsWithADescription(){
        String listDescription = "Bootcamp bootcamp go away";
        ValidatableResponse response = this.createAndManageLists.createList("Oh! look what it is, its a list","public",listDescription);
        //System.out.println(response.extract().body().asPrettyString());
        String actualListMode = response.extract().body().path("description");
        Assert.assertEquals(actualListMode,listDescription,"The list does not have a description");
    }



}
