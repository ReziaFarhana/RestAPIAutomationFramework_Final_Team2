package userstest;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import users.FollowSearchAndGetUsers;

public class FollowSearchAndGetUsersTest {
    private FollowSearchAndGetUsers followSearchAndGetUsers;


    @BeforeClass
    public void setUpTweetAPI() {
        this.followSearchAndGetUsers = new FollowSearchAndGetUsers();
    }

@Test//1
    public void testUserCanGetFollowersList(){
    ValidatableResponse response = this.followSearchAndGetUsers.getFollowersList("IsratReto");
}























}
