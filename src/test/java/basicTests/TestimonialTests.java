package basicTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotNull;

public class TestimonialTest {
    String token;
    String testimonialId;

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = "https://www.ndosiautomation.co.za/APIDEV";

        // LOGIN
        Response loginResponse = given()
                .header("Content-Type", "application/json")
                .body("""
                        {
                          "email":"admin@gmail.com",
                          "password":"@12345678"
                        }
                        """)
                .when()
                .post("/login");

        token = loginResponse.jsonPath().getString("data.token");

        System.out.println("TOKEN: " + token);
    }

    @Test(priority = 1)
    public void createTestimonial() {

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body("""
                        {
                          "title": "Great Course",
                          "content": "I am enjoying the course",
                          "rating": 5,
                          "isPublic": true
                        }
                        """)
                .when()
                .post("/testimonials");

        response.prettyPrint();

        // Extract testimonial ID
        testimonialId = response.jsonPath().getString("data.Id");

        System.out.println("TESTIMONIAL ID: " + testimonialId);

        // Assertions
        response.then().statusCode(201);

        assertNotNull(testimonialId);
    }

    @Test(priority = 2)
    public void updateTestimonial() {

        given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .pathParam("id", testimonialId)
                .body("""
                        {
                          "name":"Nqobile Updated",
                          "message":"Excellent automation training"
                        }
                        """)
                .when()
                .put("/testimonials/{id}")
                .then()
                .statusCode(200);
    }

    @Test(priority = 3)
    public void deleteTestimonial() {

        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", testimonialId)
                .when()
                .delete("/testimonials/{id}")
                .then()
                .statusCode(200);
    }

    @Test(priority = 4)
    public void getDeletedTestimonial() {

        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", testimonialId)
                .when()
                .get("/testimonials/{id}")
                .then()
                .statusCode(404);
    }

}
