import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiRecentlyPlayedSongsTests {
    private static final String baseUrl = "https://qa.koel.app";
    private static String token;
    private static final int songCount = 15;
    private static final String songCountAbc = "fkjgdsfn";
    private static final String songCountSpecChars = "@#$%&";
    private static final int songCountNegative = -10;
    private static final int songCountOverMax = 16;
    private static final int songCountMiddle = 8;
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
        Assert.assertEquals(statusCode, 200, "Login request has status code: " + statusCode);

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
    public void testRecentlyPlayedSongsWithCorrectSongCount() {
        String apiUrl = "/api/interaction/recently-played/" + songCount;
        String url = RestAssured.baseURI + apiUrl;
        Assert.assertNotNull(token, "Token is null or empty");
        Assert.assertTrue(token.matches("[a-zA-Z0-9|]+"), "Token has invalid format");
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .get(url);
        Assert.assertNotNull(response, "Response is null or empty");
        Assert.assertEquals(response.getStatusCode(), 200, "Recently played songs request with correct songCount failed with status code: " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getList("$").size(), songCount);
    }
    @Test
    public void testRecentlyPlayedSongsWithMiddleSongCount() {
        String apiUrl = "/api/interaction/recently-played/" + songCountMiddle;
        String url = RestAssured.baseURI + apiUrl;
        Assert.assertNotNull(token, "Token is null or empty");
        Assert.assertTrue(token.matches("[a-zA-Z0-9|]+"), "Token has invalid format");
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .get(url);
        Assert.assertNotNull(response, "Response is null or empty");
        Assert.assertEquals(response.getStatusCode(), 200, "Recently played songs request with middle songCount failed with status code: " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getList("$").size(), songCountMiddle);
    }
    @Test
    public void testRecentlyPlayedSongsWithOverMaxSongCount() {
        String apiUrl = "/api/interaction/recently-played/" + songCountOverMax;
        String url = RestAssured.baseURI + apiUrl;
        Assert.assertNotNull(token, "Token is null or empty");
        Assert.assertTrue(token.matches("[a-zA-Z0-9|]+"), "Token has invalid format");
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .get(url);
        Assert.assertNotNull(response, "Response is null or empty");
        Assert.assertEquals(response.getStatusCode(), 200, "Recently played songs request with overMax songCount failed with status code: " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getList("$").size(), songCount);
    }
    @Test
    public void testRecentlyPlayedSongsWithAlphabeticSongCount() {
        String apiUrl = "/api/interaction/recently-played/" + songCountAbc;
        String url = RestAssured.baseURI + apiUrl;
        Assert.assertNotNull(token, "Token is null or empty");
        Assert.assertTrue(token.matches("[a-zA-Z0-9|]+"), "Token has invalid format");
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .get(url);
        Assert.assertNotNull(response, "Response is null or empty");
        Assert.assertEquals(response.getStatusCode(), 404, "Recently played songs request with alphabetic songCount failed with status code: " + response.getStatusCode());
    }
    @Test
    public void testRecentlyPlayedSongsWithSpecialCharsSongCount() {
        String apiUrl = "/api/interaction/recently-played/" + songCountSpecChars;
        String url = RestAssured.baseURI + apiUrl;
        Assert.assertNotNull(token, "Token is null or empty");
        Assert.assertTrue(token.matches("[a-zA-Z0-9|]+"), "Token has invalid format");
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .get(url);
        Assert.assertNotNull(response, "Response is null or empty");
        Assert.assertEquals(response.getStatusCode(), 404, "Recently played songs request with specialChars songCount failed with status code: " + response.getStatusCode());
    }
    @Test
    public void testRecentlyPlayedSongsWithNegativeSongCount() {
        String apiUrl = "/api/interaction/recently-played/" + songCountNegative;
        String url = RestAssured.baseURI + apiUrl;
        Assert.assertNotNull(token, "Token is null or empty");
        Assert.assertTrue(token.matches("[a-zA-Z0-9|]+"), "Token has invalid format");
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .get(url);
        Assert.assertNotNull(response, "Response is null or empty");
        Assert.assertEquals(response.getStatusCode(), 404, "Recently played songs request with negative songCount failed with status code: " + response.getStatusCode());
    }
    @Test
    public void testRecentlyPlayedSongsWithExpiredToken() {
        String apiUrl = "/api/interaction/recently-played/" + songCount;
        String url = RestAssured.baseURI + apiUrl;
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + tokenExpired)
                .get(url);
        Assert.assertNotNull(response, "Response is null or empty");
        Assert.assertEquals(response.getStatusCode(), 401, "Recently played songs request with expired token failed with status code: " + response.getStatusCode());
    }
    @Test
    public void testRecentlyPlayedSongsWithLongToken() {
        String apiUrl = "/api/interaction/recently-played/" + songCount;
        String url = RestAssured.baseURI + apiUrl;
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + tokenLong)
                .get(url);
        Assert.assertNotNull(response, "Response is null or empty");
        Assert.assertEquals(response.getStatusCode(), 403, "Recently played songs request with long token failed with status code: " + response.getStatusCode());
    }
    @Test
    public void testRecentlyPlayedSongsWithShortToken() {
        String apiUrl = "/api/interaction/recently-played/" + songCount;
        String url = RestAssured.baseURI + apiUrl;
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + tokenShort)
                .get(url);
        Assert.assertNotNull(response, "Response is null or empty");
        Assert.assertEquals(response.getStatusCode(), 403, "Recently played songs request with short token failed with status code: " + response.getStatusCode());
    }
    @Test
    public void testRecentlyPlayedSongsWithIncorrectToken() {
        String apiUrl = "/api/interaction/recently-played/" + songCount;
        String url = RestAssured.baseURI + apiUrl;
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + tokenIncorrect)
                .get(url);
        Assert.assertNotNull(response, "Response is null or empty");
        Assert.assertEquals(response.getStatusCode(), 403, "Recently played songs request with incorrect token failed with status code: " + response.getStatusCode());
    }
    @Test
    public void testRecentlyPlayedSongsWithMissedToken() {
        String apiUrl = "/api/interaction/recently-played/" + songCount;
        String url = RestAssured.baseURI + apiUrl;
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + tokenMissed)
                .get(url);
        Assert.assertNotNull(response, "Response is null or empty");
        Assert.assertEquals(response.getStatusCode(), 401, "Recently played songs request with missed token failed with status code: " + response.getStatusCode());
    }
    @Test
    public void testRecentlyPlayedSongsWithIncorrectMethod() {
        String apiUrl = "/api/interaction/recently-played/" + songCount;
        String url = RestAssured.baseURI + apiUrl;
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .post(url);
        String responseBody = response.getBody().asString();
        Assert.assertNotNull(response, "Response is null or empty");
        Assert.assertEquals(response.getStatusCode(), 405, "Recently played songs request with incorrect method failed with status code: " + response.getStatusCode());
        Assert.assertTrue(responseBody.contains("405 Method Not Allowed"), "Body does not contain Method not allowed error message");
    }
}
