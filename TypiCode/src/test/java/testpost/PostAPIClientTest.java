package testpost;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import typicode.PostPojo;
import typicode.PostsAPIClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class PostAPIClientTest {
    private PostsAPIClient postsAPIClient;

    @BeforeClass
    public void setUpPostsAPI(){
        this.postsAPIClient=new PostsAPIClient();
    }

    @Test
    public void testGetAllPosts42(){
        ValidatableResponse response=this.postsAPIClient.getAllPosts();
        response.statusCode(HttpStatus.SC_OK);
        System.out.println(response.extract().body().asPrettyString());
    }

    // write a test that creates a post
    @Test
    public void testUserCanCreateAPostUsingPojoSuccessfully43() {
        PostPojo obj = new PostPojo(11, 101, "test title", "test body");
        ValidatableResponse response = this.postsAPIClient.createPost(obj);
        response.statusCode(HttpStatus.SC_CREATED);
        System.out.println(response.extract().body().asPrettyString());

    }

    @Test
    public void testUserCanCreateAPostSuccessfully44() {
        int userId = 11;
        String title = "test title";
        String body = "test body";

        JSONObject json = new JSONObject();
        json.put("userId", userId);
        json.put("id", 101);
        json.put("title", title);
        json.put("body", body);
        ValidatableResponse response = this.postsAPIClient.createPost(json);
        response.statusCode(HttpStatus.SC_CREATED);

        int actualUserId = response.extract().body().path("userId");
        String actualTitle = response.extract().body().path("title");
        String actualBody = response.extract().body().path("body");
        Assert.assertEquals(actualUserId, userId);
        Assert.assertEquals(actualTitle, title);
        Assert.assertEquals(actualBody, body);
    }
    @Test
    public void testUserCanFetchId45(){
        LinkedHashMap<String,Integer>  expectedId = new LinkedHashMap();
        expectedId.put("id", 13);
        ValidatableResponse response = this.postsAPIClient.getAllPhotos();
        response.statusCode(HttpStatus.SC_OK);
        System.out.println(response.extract().body().asPrettyString());
        LinkedHashMap actualids = response.extract().body().path("id");
        Assert.assertEquals(actualids, expectedId, "not match");

    }
    @Test
    public void testUserCanDeletePost46() {
        ValidatableResponse response = this.postsAPIClient.deletePost(2);
        response.statusCode(HttpStatus.SC_OK);
    }


    @Test
    public void testUserUploadPhoto47() {
        JSONObject obj = new JSONObject();
        obj.put("id", "30");
        ValidatableResponse response = this.postsAPIClient.uploadPhoto(obj);
        response.statusCode(HttpStatus.SC_CREATED);
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testUserUploadPhoto48() {
        String title = "reprehenderit est deserunt velit ipsam";
        ValidatableResponse response = this.postsAPIClient.getPhoto(title);
        response.statusCode(HttpStatus.SC_OK);
        System.out.println(response.extract().body().asPrettyString());
        String actualTitle = response.extract().body().path("[0].title");
        Assert.assertEquals(actualTitle,title, "title doesnt match");
    }
    @Test
    public void testUserCanGetCommentsUsingPostID49() {
        int commentCount = 5;
        ValidatableResponse response = this.postsAPIClient.getComment(3);
        response.statusCode(HttpStatus.SC_OK);
        System.out.println(response.extract().body().asPrettyString());
        List<Integer> actualCommentCount = (response.extract().body().jsonPath().getList("postID"));
        Assert.assertEquals(actualCommentCount.size(),commentCount, "count doesnt match");
    }

    @Test
    public void testGetAllUsers50(){
        int phoneCount = 10;
        ValidatableResponse response = this.postsAPIClient.getAllUsers();
        System.out.println(response.extract().body().asPrettyString());
        List<Integer> actualPhoneCount = (response.extract().body().jsonPath().getList("phone"));
        Assert.assertEquals(actualPhoneCount.size(), phoneCount, "Count doesnt match");

    }



}
