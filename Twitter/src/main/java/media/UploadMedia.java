package media;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.*;

public class UploadMedia extends RestAPI {
    private final String POST_MEDIA_UPLOAD = "/media/upload.json";
    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String UPLOAD_PROFILE_IMAGE_ENDPOINT = "/account/update_profile_image.json";
    private final String UPLOAD_PROFILE_BANNER_ENDPOINT = "/account/update_profile_banner.json";
    private final String CREATE_DIRECT_MESSAGES_ENDPOINT = "/direct_messages/events/new.json";
    private final String imagePath = "C:\\Users\\israt\\IdeaProjects\\RestAPIAutomationFramework_Final_Team2\\Twitter\\jsonFiles\\DirectMessageWithImage.json";
    private final String path = "C:\\Users\\israt\\IdeaProjects\\RestAPIAutomationFramework_Final_Team2\\Twitter\\jsonFiles\\WelcomeMessage.json";
    private final String POST_WELCOME_MESSAGES_ENDPOINT = "/direct_messages/welcome_messages/new.json";


    public ValidatableResponse uploadImage(String path) throws IOException {
        //  FileInputStream image = new FileInputStream("C:\\Users\\israt\\IdeaProjects\\RestAPIAutomationFramework_Final_Team2\\Payload\\ImageOneFile.txt");
        String image = new String(Files.readAllBytes(Path.of(path)));
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
//            .body(image)
                .param("media", image)
                .when().post(this.uploadImageBaseUrl + this.POST_MEDIA_UPLOAD).then();
    }

    public ValidatableResponse createTweetWithAImage(String tweet, long mediaID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .param("media_ids", mediaID)
                .when().post(this.baseUrl + this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    public ValidatableResponse updateProfileImage(String path) throws IOException {
        String image = new String(Files.readAllBytes(Path.of(path)));
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("image", image)
                .when().post(this.baseUrl + this.UPLOAD_PROFILE_IMAGE_ENDPOINT).then();
    }

    public ValidatableResponse updateProfileBanner(String path) throws IOException {
        String banner = new String(Files.readAllBytes(Path.of(path)));
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("banner", banner)
                .when().post(this.baseUrl + this.UPLOAD_PROFILE_BANNER_ENDPOINT).then();
    }

    //send an image as a attachment
    public ValidatableResponse createDirectMessagesWithImageAttachment( ) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(imagePath);
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).header("Content-type","application/json").and()
                .body(inputStream)
                .when().post(this.baseUrl + this.CREATE_DIRECT_MESSAGES_ENDPOINT).then().extract().response().then();
    }

    //chunk upload
//    public ValidatableResponse chunkImageUpload(String chunkImagePath,String mediaCategory, boolean shared ,String command, int totalByte, String mediaType) throws IOException {
//        String image = new String(Files.readAllBytes(Path.of(chunkImagePath)));
//        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).header("Content-type", "application/json")
//                .param("media_category",mediaCategory)
//                .param("shared",shared)
//                .param("command",command)
//                .param("total_bytes",totalByte)
//                .param("media_type",mediaType)
//                .param("media",image)
//                .when().post(this.uploadImageBaseUrl + this.POST_MEDIA_UPLOAD).then();
//
//    }

    public ValidatableResponse chunkImageUpload(String chunkImagePath,String mediaCategory, boolean shared ,String command, int totalByte, String mediaType) throws IOException {
        String image = new String(Files.readAllBytes(Path.of(chunkImagePath)));
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).header("Content-type", "application/json")
                .param("media_category",mediaCategory)
                .param("shared",shared)
                .param("command",command)
                .param("total_bytes",totalByte)
                .param("media_type",mediaType)
                .param("media",image)
                .when().post(this.uploadImageBaseUrl + this.POST_MEDIA_UPLOAD).then();

    }

    public ValidatableResponse createWelcomeMessage() throws FileNotFoundException {
        FileInputStream welcomeMessage = new FileInputStream(path);
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret).header("Content-type", "application/json")
                .body(welcomeMessage)
                .when().post(this.baseUrl + this.POST_WELCOME_MESSAGES_ENDPOINT).then();
    }







}
