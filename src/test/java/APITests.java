import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APITests {
    private String baseUrl = "https://qa.koel.app/api";
    private String token;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = baseUrl;
        //make API call and retrieve token
        /*Response response = given()
                .contentType(ContentType.JSON)
                .post("/me");
        token = response.jsonPath().getString("token");*/

        Response response = RestAssured.get("/me");
        System.out.println(response.getHeaders().get("Content-Type"));
        System.out.println(response.getBody().asString());
    }
    @Test
    public void testLoginStatusCode() {
        Response response = given().post("/me");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Status code is not 200");
    }
    @Test
    public void testBodyContainsToken() {
        Response response = given().post("/me");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("token"), "Body does not contain token");
    }
    @Test(dependsOnMethods = {"testLoginStatusCode", "testBodyContainsToken"})
    public void testTokenValue() {
        Assert.assertNotNull(token, "Token is null");
    }
}
