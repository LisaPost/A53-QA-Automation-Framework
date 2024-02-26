import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APITests {
    private static final String baseUrl = "https://qa.koel.app/api";
    private static String token;
    private static final String songIdCorrect = "1bfa5b75745ebb5285b822f4b16899fd";
//3d98035ebc9b79381d0a0bec8be9b1c9
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = baseUrl;
        token = loginAndGetToken();
        //token = loginAndGetResponse();
    }
    private String loginAndGetToken() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "yelyzaveta.postnova@testpro.io");
        requestBody.put("password", "YrkeNi92");

        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/me");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Login request failed with status code: " + statusCode);

        return response.jsonPath().getString("token");
    }
    private Response loginAndGetResponse() {
        // Define the request body for login
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "yelyzaveta.postnova@testpro.io");
        requestBody.put("password", "YrkeNi92");

        // Send the POST request with the request body
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/me");

        //System.out.println("Response body: " + response.getBody().asString());
        return response;
    }
    private String getTokenFromResponse(Response response) {
        // Extract and return the token from the response
        return response.jsonPath().getString("token");
    }

    @Test(priority = 1)
    public void testLoginStatusCode() {
        Response response = loginAndGetResponse();
        //Response response = loginAndGetToken();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        Assert.assertEquals(statusCode, 200, "Login request failed with status code: " + statusCode);
        Assert.assertTrue(responseBody.contains("token"), "Body does not contain token");
        Assert.assertNotNull(token, "Token is null");

        token = getTokenFromResponse(response);
        System.out.println("Login status code: " + statusCode);
        System.out.println("token: " + token);
        System.out.println("Response body: " + response.getBody().asString());
    }
    private Response loginPlaySongAndGetResponse() {
        Response response = given()
                .header("api-token", token)
                .get("/play/" + songIdCorrect);
        return response;
    }

    @Test
    public void playSongWithValidSongID() {
        Response loginResponse = loginAndGetResponse();
        token = getTokenFromResponse(loginResponse);
        System.out.println("Token used for request: " + token);
        System.out.println("Song ID used for request: " + songIdCorrect);
        Response response = given()
                .header("api-token", token)
                .get("/play/" + songIdCorrect);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        Assert.assertEquals(statusCode, 200, "Play a song with valid songID failed with status code: " + statusCode);
        Assert.assertTrue(responseBody.contains("It's Your Birthday!"), "Body does not contain song name");
        System.out.println("Play song status code: " + statusCode);
        System.out.println("Response body: " + response.getBody().asString());
    }
    /*@Test
    public void playSongWithValidSongID() {
        Response response = loginPlaySongAndGetResponse();
        token = getTokenFromResponse(response);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        Assert.assertEquals(statusCode, 200, "Play a song with valid songID failed with status code: " + statusCode);
        Assert.assertTrue(responseBody.contains("It's Your Birthday!"), "Body does not contain song name");
        //token = getTokenFromResponse(response);
        System.out.println("status code: " + statusCode);
        System.out.println("token: " + token);
        System.out.println("Response body: " + response.getBody().asString());
    }*/
}
