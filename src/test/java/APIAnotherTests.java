
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class APIAnotherTests {

    /*private String baseURL;
    private String token;
    private static final String songIdCorrect = "3d98035ebc9b79381d0a0bec8be9b1c9";
    private static final String songIdIncorrect = "3d98035ebc9b79381d0a0bec8be44444";

    @BeforeClass
    public void setUp() {
        baseURL = "https://qa.koel.app/api";
    }


    @Test
    public void testPostAPI() {
        String email = "yelyzaveta.postnova@testpro.io";
        String password = "YrkeNi92";

        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .post(baseURL + "/me");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        token = response.jsonPath().getString("token");
        System.out.println("Login status code: " + statusCode);
        System.out.println("token: " + token);
        System.out.println("Response body: " + response.getBody().asString());
    }
    @Test(dependsOnMethods = "testPostAPI")
    public void testGetCorrectSong() {
        testGetSongWithToken(songIdCorrect, token, 200, "It's Your Birthday!");
    }

    @Test(dependsOnMethods = "testPostAPI")
    public void testGetIncorrectSong() {
        testGetSongWithToken(songIdIncorrect, token, 404, "Requested song doesn’t exist");
    }

    @Test(dependsOnMethods = "testPostAPI")
    public void testPutMethodNotAllowed() {
        testPutMethodWithToken(songIdCorrect, token, 405, "The PUT method is not supported for this route. Supported methods: GET, HEAD.");
    }

    private void testGetSongWithToken(String songId, String token, int expectedStatusCode, String expectedMessage) {
        Response response = RestAssured.get(baseURL + "/play/" + songId + "/?api-token=" + token);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, expectedStatusCode);
        Assert.assertTrue(response.getBody().asString().contains(expectedMessage));
        System.out.println("Song status code: " + statusCode);
        System.out.println("token: " + token);
    }

    private void testPutMethodWithToken(String songId, String token, int expectedStatusCode, String expectedMessage) {
        Response response = RestAssured.put(baseURL + "/play/" + songId + "/?api-token=" + token);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, expectedStatusCode);
        Assert.assertTrue(response.getBody().asString().contains(expectedMessage));
        System.out.println("Song status code: " + statusCode);
        System.out.println("token: " + token);
    }
}

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class APIAnotherTests {

    private String baseURL;
    private String token;
    private static final String songIdCorrect = "3d98035ebc9b79381d0a0bec8be9b1c9";
    private static final String songIdIncorrect = "3d98035ebc9b79381d0a0bec8be44444";

    @BeforeClass
    public void setUp() {
        baseURL = "https://qa.koel.app/api";
    }

    @Test
    public void testPostAPI() {
        String email = "yelyzaveta.postnova@testpro.io";
        String password = "YrkeNi92";

        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .post(baseURL + "/me");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        token = response.jsonPath().getString("token");
        System.out.println("Login status code: " + statusCode);
        System.out.println("token: " + token);
        System.out.println("Response body: " + response.getBody().asString());
    }

    @Test(dependsOnMethods = "testPostAPI")
    public void testGetCorrectSong() {
        Response response = RestAssured.get(baseURL + "/play/" + songIdCorrect + "/?api-token=" + token);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        Assert.assertTrue(response.getBody().asString().contains("It's Your Birthday!"));
        System.out.println("Correct Song status code: " + statusCode);
        System.out.println("token: " + token);
    }

    @Test(dependsOnMethods = "testPostAPI")
    public void testGetIncorrectSong() {
        Response response = RestAssured.get(baseURL + "/play/" + songIdIncorrect + "/?api-token=" + token);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
        Assert.assertTrue(response.getBody().asString().contains("Requested song doesn’t exist"));
        System.out.println("Incorrect Song status code: " + statusCode);
        System.out.println("token: " + token);
    }

    @Test(dependsOnMethods = "testPostAPI")
    public void testPutMethodNotAllowed() {
        Response response = RestAssured.put(baseURL + "/play/" + songIdCorrect + "/?api-token=" + token);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 405);
        Assert.assertTrue(response.getBody().asString().contains("The PUT method is not supported for this route. Supported methods: GET, HEAD."));
        System.out.println("Incorrect Song status code: " + statusCode);
        System.out.println("token: " + token);
    }*/
}

