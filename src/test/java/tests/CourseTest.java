package tests;

import base.BaseTest;
import builders.RequestBuilder;
import endpoints.Endpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CourseTest extends BaseTest {

    @Test(priority = 6)
    public void getCourseTest() {

        Response response =
                RequestBuilder.getRequestSpec()
                        .queryParam("category", "automation")
                        .queryParam("level", "beginner")
                        .log().all()
                        .get(Endpoints.COURSES);

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}