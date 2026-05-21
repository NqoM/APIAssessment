package tests;

import base.BaseTest;
import endpoints.Endpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NegativeTest extends BaseTest {

    @Test
    public void createTestimonialWithoutTokenTest() {

        String payload = "{\n" +
                "  \"title\": \"Invalid Testimonial\",\n" +
                "  \"content\": \"This should fail\",\n" +
                "  \"rating\": 5,\n" +
                "  \"isPublic\": true\n" +
                "}";

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(payload)
                        .post(Endpoints.TESTIMONIALS)
                        .then()
                        .extract().response();

        Assert.assertEquals(response.getStatusCode(), 401);

        System.out.println("NEGATIVE TEST PASSED");
    }
}
