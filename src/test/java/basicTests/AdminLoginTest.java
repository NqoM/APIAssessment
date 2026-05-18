package basicTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class AdminLoginTest {


        String token;

        @Test
        public void adminLoginTest() {

            RestAssured.baseURI = "https://www.ndosiautomation.co.za/APIDEV";

            Response response = given()
                    .header("Content-Type", "application/json")
                    .body("""
                        {
                          "email":"admin@gmail.com",
                          "password":"@12345678"
                        }
                        """)
                    .when()
                    .post("/login");

            response.prettyPrint();

            // Extract token
            token = response.jsonPath().getString("data.token");

            System.out.println("TOKEN IS: " + token);

            // Assertion
            Assert.assertNotNull(token);
        }
    }