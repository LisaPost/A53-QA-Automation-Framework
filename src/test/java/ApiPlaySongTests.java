import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiPlaySongTests {
    private static final String baseUrl = "https://qa.koel.app";
    private static String token;
    private static final String songIdCorrect = "3d98035ebc9b79381d0a0bec8be9b1c9"; //"3dfc5899aed1fb7edba2ce89d8d8ec45";
    private static final String songIdIncorrect = "3d98035ebc9b79381d0a0bec8be44444";
    private static final String songIdLong = "3d98035ebc9b79381d0a0bec8be9b1c91";
    private static final String songIdShort = "3d98035ebc9b79381d0a0bec8be9b1c";
    private static final String tokenExpired = "291795|gFueRC5nWXqe9NP7UySBIsTT6jGFbgagJNJTD6oo";
    private static final String tokenIncorrect = "291795|gFueRC5nWXqe9NP7UySBIsTT6jGFbgagJNJTD5oo";
    private static final String tokenLong = "291795|gFueRC5nWXqe9NP7UySBIsTT6jGFbgagJNJTD6oo1";
    private static final String tokenShort = "291795|gFueRC5nWXqe9NP7UySBIsTT6jGFbgagJNJTD6o";
    private static final String tokenMissed = "";
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = baseUrl;
        token = loginAndGetToken();
    }
    private String loginAndGetToken() {
        Map<String, String> loginRequestBody = new HashMap<>();
        loginRequestBody.put("email", "yelyzaveta.postnova@testpro.io");
        loginRequestBody.put("password", "YrkeNi92");
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(loginRequestBody)
                .post("/api/me");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Login request failed with status code: " + statusCode);

        return response.jsonPath().getString("token");
    }
    private Response loginAndGetResponse() {
        Map<String, String> loginRequestBody = new HashMap<>();
        loginRequestBody.put("email", "yelyzaveta.postnova@testpro.io");
        loginRequestBody.put("password", "YrkeNi92");
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(loginRequestBody)
                .post("/api/me");
        return response;
    }
    private String getTokenFromResponse(Response response) {
        return response.jsonPath().getString("token");
    }
    @Test
    public void testLogin() {
        Response response = loginAndGetResponse();
        int loginStatusCode = response.getStatusCode();
        String loginResponseBody = response.getBody().asString();
        Assert.assertEquals(loginStatusCode, 200, "Login request failed with status code: " + loginStatusCode);
        Assert.assertTrue(loginResponseBody.contains("token"), "Body does not contain token");
        Assert.assertNotNull(token, "Token is null or empty");
        Assert.assertTrue(token.matches("[a-zA-Z0-9|]+"), "Token has invalid format");
        System.out.println("Login status code: " + loginStatusCode);
    }
    @Test
    public void testPlaySongWithCorrectSongID() {
        String apiUrl = "/play/" + songIdCorrect + "?api_token=" + token;
        String url = RestAssured.baseURI + apiUrl;
        Response response = given().queryParam("api-token", token).get(url);
        int playSongStatusCode = response.getStatusCode();
        String playSongResponseBody = response.getBody().asString();
        Assert.assertEquals(playSongStatusCode, 200, "Play a song with correct songID fails with status code: " + playSongStatusCode);
        Assert.assertTrue(playSongResponseBody.contains("It's Your Birthday!"), "Body does not contain expected song name");
        Assert.assertNotNull(response, "Response is null or empty");
        System.out.println("Play song with correct songID status code: " + playSongStatusCode);
        System.out.println("Song name corresponds songID");
    }
    @Test
    public void testPlaySongWithIncorrectSongID() {
        String apiUrl = "/play/" + songIdIncorrect + "?api_token=" + token;
        String url = RestAssured.baseURI + apiUrl;
        Response response = given().queryParam("api-token", token).get(url);
        int playSongStatusCode = response.getStatusCode();
        String playSongResponseBody = response.getBody().asString();
        Assert.assertEquals(playSongStatusCode, 404, "Play a song with incorrect songID fails with status code: " + playSongStatusCode);
        Assert.assertTrue(playSongResponseBody.contains("Requested song doesn’t exist"), "Body does not contain 'Requested song does not exist' error message");
        Assert.assertNotNull(response, "Response is null or empty");
        System.out.println("Play song with incorrect songID status code: " + playSongStatusCode);
    }
    @Test
    public void testPlaySongWithMissedSongID() {
        String apiUrl = "/play/" + "?api_token=" + token;
        String url = RestAssured.baseURI + apiUrl;
        Response response = given().queryParam("api-token", token).get(url);
        int playSongStatusCode = response.getStatusCode();
        Assert.assertEquals(playSongStatusCode, 404, "Play a song with missed songID fails with status code: " + playSongStatusCode);
        Assert.assertNotNull(response, "Response is null or empty");
        System.out.println("Play song with missed songID status code: " + playSongStatusCode);
    }
    @Test
    public void testPlaySongWithLongSongID() {
        String apiUrl = "/play/" + songIdLong + "?api_token=" + token;
        String url = RestAssured.baseURI + apiUrl;
        Response response = given().queryParam("api-token", token).get(url);
        int playSongStatusCode = response.getStatusCode();
        Assert.assertEquals(playSongStatusCode, 404, "Play a song with long songID fails with status code: " + playSongStatusCode);
        Assert.assertNotNull(response, "Response is null or empty");
        System.out.println("Play song with long songID status code: " + playSongStatusCode);
    }
    @Test
    public void testPlaySongWithShortSongID() {
        String apiUrl = "/play/" + songIdShort + "?api_token=" + token;
        String url = RestAssured.baseURI + apiUrl;
        Response response = given().queryParam("api-token", token).get(url);
        int playSongStatusCode = response.getStatusCode();
        Assert.assertEquals(playSongStatusCode, 404, "Play a song with short songID fails with status code: " + playSongStatusCode);
        Assert.assertNotNull(response, "Response is null or empty");
        System.out.println("Play song with short songID status code: " + playSongStatusCode);
    }
    @Test
    public void testPlaySongWithExpiredToken() {
        String apiUrl = "/play/" + songIdCorrect + "?api_token=" + tokenExpired;
        String url = RestAssured.baseURI + apiUrl;
        Response response = given().queryParam("api-token", tokenExpired).get(url);
        int playSongStatusCode = response.getStatusCode();
        Assert.assertEquals(playSongStatusCode, 401, "Play a song with expired token fails with status code: " + playSongStatusCode);
        Assert.assertNotNull(response, "Response is null or empty");
        System.out.println("Play song with expired token status code: " + playSongStatusCode);
    }
    @Test
    public void testPlaySongWithLongToken() {
        String apiUrl = "/play/" + songIdCorrect + "?api_token=" + tokenLong;
        String url = RestAssured.baseURI + apiUrl;
        Response response = given().queryParam("api-token", tokenLong).get(url);
        int playSongStatusCode = response.getStatusCode();
        Assert.assertEquals(playSongStatusCode, 403, "Play a song with long token fails with status code: " + playSongStatusCode);
        Assert.assertNotNull(response, "Response is null or empty");
        System.out.println("Play song with long token status code: " + playSongStatusCode);
    }
    @Test
    public void testPlaySongWithShortToken() {
        String apiUrl = "/play/" + songIdCorrect + "?api_token=" + tokenShort;
        String url = RestAssured.baseURI + apiUrl;
        Response response = given().queryParam("api-token", tokenShort).get(url);
        int playSongStatusCode = response.getStatusCode();
        Assert.assertEquals(playSongStatusCode, 403, "Play a song with short token fails with status code: " + playSongStatusCode);
        Assert.assertNotNull(response, "Response is null or empty");
        System.out.println("Play song with short token status code: " + playSongStatusCode);
    }
    @Test
    public void testPlaySongWithIncorrectToken() {
        String apiUrl = "/play/" + songIdCorrect + "?api_token=" + tokenIncorrect;
        String url = RestAssured.baseURI + apiUrl;
        Response response = given().queryParam("api-token", tokenIncorrect).get(url);
        int playSongStatusCode = response.getStatusCode();
        Assert.assertEquals(playSongStatusCode, 403, "Play a song with incorrect token fails with status code: " + playSongStatusCode);
        Assert.assertNotNull(response, "Response is null or empty");
        System.out.println("Play song with incorrect token status code: " + playSongStatusCode);
    }
    @Test
    public void testPlaySongWithMissedToken() {
        String apiUrl = "/play/" + songIdCorrect + "?api_token=" + tokenMissed;
        String url = RestAssured.baseURI + apiUrl;
        Response response = given().queryParam("api-token", tokenMissed).get(url);
        int playSongStatusCode = response.getStatusCode();
        Assert.assertEquals(playSongStatusCode, 401, "Play a song with missed token fails with status code: " + playSongStatusCode);
        Assert.assertNotNull(response, "Response is null or empty");
        System.out.println("Play song with missed token status code: " + playSongStatusCode);
    }
    @Test
    public void testPlaySongWithIncorrectMethod() {
        String apiUrl = "/play/" + songIdCorrect + "?api_token=" + token;
        String url = RestAssured.baseURI + apiUrl;
        Response response = given().queryParam("api-token", token).post(url);
        int playSongStatusCode = response.getStatusCode();
        String playSongResponseBody = response.getBody().asString();
        Assert.assertEquals(playSongStatusCode, 405, "Play song with incorrect method fails with status code: " + playSongStatusCode);
        Assert.assertTrue(playSongResponseBody.contains("405 Method Not Allowed"), "Body does not contain Method not allowed error message");
        Assert.assertNotNull(response, "Response is null or empty");
        System.out.println("Play song with incorrect method status code: " + playSongStatusCode);
    }
}
