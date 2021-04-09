package mediatest;

import io.restassured.response.ValidatableResponse;
import media.UploadMedia;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadMediaTest {
    private static final String filePath = "C:\\Users\\israt\\IdeaProjects\\RestAPIAutomationFramework_Final_Team2\\Payload\\";
    private UploadMedia uploadMedia;

    @BeforeClass
    public void setUpTweetAPI() {
        this.uploadMedia = new UploadMedia();
    }

    @Test //1
    public void TestUserCanUploadImage() throws IOException {
        String path = filePath + "CuteImage2.txt";
        ValidatableResponse response = this.uploadMedia.uploadImage(path);
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testUserCanCreateATweetWithAImage() {
        String tweet = "I can finally experience Easha's excitement";
        ValidatableResponse response = this.uploadMedia.createTweetWithAImage(tweet, 1380265595652993029L);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);
    }

    @Test //2
    public void testUserCanUpdateProfileImage() throws IOException {
        String imagePath = filePath + "ImageThreeFile.txt";
        ValidatableResponse response = this.uploadMedia.updateProfileImage(imagePath);
        //System.out.println(response.extract().body().asPrettyString());
        boolean actualProfileUseBackground = response.extract().body().path("profile_use_background_image");
        Assert.assertTrue(actualProfileUseBackground);
    }

    @Test //3
    public void testUserCanUpdateProfileBanner() throws IOException {
        String imagePath = filePath + "ImageOneFile.txt";
        ValidatableResponse response = this.uploadMedia.updateProfileBanner(imagePath);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_CREATED);
    }

    @Test //4
    public void testUserCanCreateDirectMessagesWithImageAttachment() throws FileNotFoundException {
        long recipientID = 1376273647225167881L;
        ValidatableResponse response = this.uploadMedia.createDirectMessagesWithImageAttachment();
        System.out.println(response.extract().body().asPrettyString());
        long actualRecipientId = response.extract().body().path("event.message_create.target.recipient_id");
        Assert.assertEquals(actualRecipientId,recipientID,"It is not the same ID");
    }

    @Test //5
    public void testUserCanCreateDirectMessagesWithImageAttachment2() throws FileNotFoundException {
        long recipientID = 1376271531341398017L;
        ValidatableResponse response = this.uploadMedia.createDirectMessagesWithImageAttachment();
        System.out.println(response.extract().body().asPrettyString());
        long actualRecipientId = response.extract().body().path("event.message_create.target.recipient_id");
        Assert.assertEquals(actualRecipientId,recipientID,"It is not the same ID");
    }

    @Test //6
    public void testUserCanCreateChunkImageUpload() throws IOException {
        String path = filePath + "CuteImage2.txt";
        ValidatableResponse response = this.uploadMedia.chunkImageUpload(path,"dm_image",true,"INIT",31054,"image/jpeg");
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testUserCanCreateWelcomeMessage() throws FileNotFoundException {
        ValidatableResponse response = this.uploadMedia.createWelcomeMessage();
        System.out.println(response.extract().body().asPrettyString());
    }

}
