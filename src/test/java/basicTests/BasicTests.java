package basicTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicTests {

    String BaseURL = "https://www.ndosiautomation.co.za/APIDEV";
    String token;
    String testimonialId;

    // ---------------- LOGIN ----------------
    @Test(priority = 1)
    public void adminLoginTest() {

        String loginPayload =
                "{\n" +
                        "  \"email\": \"admin@gmail.com\",\n" +
                        "  \"password\": \"@12345678\"\n" +
                        "}";

        Response response = RestAssured.given()
                .baseUri(BaseURL)
                .basePath("/login")
                .header("Content-Type", "application/json")
                .body(loginPayload)
                .log().all()
                .post()
                .then().extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);

        token = response.jsonPath().getString("data.token");

        System.out.println("TOKEN: " + token);
    }

    // ---------------- CREATE TESTIMONIAL ----------------
    @Test(priority = 2)
    public void createTestimonialTest() {

        String payload =
                "{\n" +
                        "  \"title\": \"Great Course\",\n" +
                        "  \"content\": \"I am enjoying the course\",\n" +
                        "  \"rating\": 5,\n" +
                        "  \"isPublic\": true\n" +
                        "}";

        Response response = RestAssured.given()
                .baseUri(BaseURL)
                .basePath("/testimonials")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .log().all()
                .post()
                .then().extract().response();

        Assert.assertEquals(response.getStatusCode(), 201);

        testimonialId = response.jsonPath().getString("data.Id");

        System.out.println("TESTIMONIAL ID: " + testimonialId);
    }

    // ---------------- UPDATE TESTIMONIAL ----------------
    @Test(priority = 3)
    public void updateTestimonialTest() {

        String payload =
                "{\n" +
                        "  \"title\": \"Updated Title\",\n" +
                        "  \"content\": \"Updated content\",\n" +
                        "  \"rating\": 4\n" +
                        "}";

        Response response = RestAssured.given()
                .baseUri(BaseURL)
                .basePath("/testimonials/" + testimonialId)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .log().all()
                .put()
                .then().extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // ---------------- DELETE TESTIMONIAL ----------------
    @Test(priority = 4)
    public void deleteTestimonialTest() {

        Response response = RestAssured.given()
                .baseUri(BaseURL)
                .basePath("/testimonials/" + testimonialId)
                .header("Authorization", "Bearer " + token)
                .log().all()
                .delete()
                .then().extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // ---------------- VERIFY DELETE ----------------
    @Test(priority = 5)
    public void verifyDeletedTestimonialTest() {

        Response response = RestAssured.given()
                .baseUri(BaseURL)
                .basePath("/testimonials/" + testimonialId)
                .log().all()
                .get()
                .then().extract().response();

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    // ---------------- GET COURSES ----------------
    @Test(priority = 6)
    public void getCoursesTest() {

        Response response = RestAssured.given()
                .baseUri(BaseURL)
                .basePath("/courses")
                .queryParam("category", "automation")
                .queryParam("level", "beginner")
                .header("Content-Type", "application/json")
                .log().all()
                .get()
                .then().extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println("COURSES RESPONSE:");
        System.out.println(response.asPrettyString());
    }
}



// All tests from Postman