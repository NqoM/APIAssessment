package tests;

import base.BaseTest;
import builders.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CourseTest extends BaseTest {

    @Test
    public void getCoursesTest() {

        Response response =
                builders.RequestBuilder.getRequest()
                        .queryParam("category", "automation")
                        .queryParam("level", "beginner")
                        .get(endpoints.Endpoints.COURSES)
                        .then()
                        .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println(response.asPrettyString());
    }
}