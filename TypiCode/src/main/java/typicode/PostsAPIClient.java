package typicode;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PostsAPIClient extends TypiCodeAPIClient {

    private String POST_ENDPOINT = "/posts";
    private String PHOTOS_ENDPOINT = "/photos";
    private String POST_DELETE= "/posts/1";
    private String PUT_POST_ENDPOINT= "/posts/1";
    private String COMMENTS_ENDPOINT= "/comments";
    private String USERS_ENDPOINT= "/users";

    public ValidatableResponse getAllPosts() {
        return when().get(this.baseUrl + POST_ENDPOINT).then();
    }

    public ValidatableResponse getAllPhotos() {
        return when().get(this.baseUrl + PHOTOS_ENDPOINT).then();
    }

    public ValidatableResponse deletePost(int id) {
        return given().queryParam("id", id)
                .when().delete(this.baseUrl + POST_DELETE).then();
    }
    public ValidatableResponse putPost(Object json){
        return given().header("Content-type","application/json").body(json)
                .when().put(this.baseUrl+PUT_POST_ENDPOINT).then();
    }



    public ValidatableResponse createPost(Object json){
        return given().header("Content-type","application/json").body(json)
                .when().post(this.baseUrl+POST_ENDPOINT).then();
    }
    public ValidatableResponse uploadPhoto(Object json){
        return given().header("Content-type","application/json").body(json)
                .when().post(this.baseUrl+PHOTOS_ENDPOINT).then();
    }
    public ValidatableResponse getPhoto(String title){
        return given().queryParam("title", title)
                .when().get(this.baseUrl+PHOTOS_ENDPOINT).then();
    }
    public ValidatableResponse getComment(int postID){
        return given().queryParam("postId", postID)
                .when().get(this.baseUrl+COMMENTS_ENDPOINT).then();
    }
    public ValidatableResponse getAllUsers(){
        return when().get(this.baseUrl+USERS_ENDPOINT).then();
    }





}
