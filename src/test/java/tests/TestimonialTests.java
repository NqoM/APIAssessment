package tests;


import base.BaseTest;
//import builders.PayloadBuilder;
import builders.RequestBuilder;
import commons.CommonData;
import endpoints.Endpoints;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestimonialTests {
    @Test(dependsOnMethods = "tests.AuthTest.adminLoginTest")
    public void createTestimonialTest() {

        String payload = "{\n" +
                "  \"title\": \"Great Course\",\n" +
                "  \"content\": \"I am enjoying the course\",\n" +
                "  \"rating\": 5,\n" +
                "  \"isPublic\": true\n" +
                "}";

        RequestBuilder RequestBuilder;
        Response response =
                builders.RequestBuilder.getRequest()
                        .body(payload)
                        .post(endpoints.Endpoints.TESTIMONIALS)
                        .then()
                        .extract().response();

        Assert.assertEquals(response.getStatusCode(), 201);

        CommonData.testimonialId = response.jsonPath().getString("data.Id");
    }

    @Test(dependsOnMethods = "createTestimonialTest")
    public void updateTestimonialTest() {

        String payload = "{\n" +
                "  \"title\": \"Updated Title\",\n" +
                "  \"content\": \"Updated content\",\n" +
                "  \"rating\": 4\n" +
                "}";

        Response response =
                RequestBuilder.getRequest()
                        .pathParam("id", CommonData.testimonialId)
                        .body(payload)
                        .put(Endpoints.TESTIMONIALS + "/{id}")
                        //.put(Endpoints.TESTIMONIALS + "/" + CommonData.testimonialId)
                        .then()
                        .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "updateTestimonialTest")
    public void deleteTestimonialTest() {

        Response response =
                builders.RequestBuilder.getRequest()
                        .pathParam("id", CommonData.testimonialId)
                        .delete(Endpoints.TESTIMONIALS + "/{id}")
                        //.delete(Endpoints.TESTIMONIALS + "/" + CommonData.testimonialId)
                        .then()
                        .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "deleteTestimonialTest")
    public void verifyDeletedTestimonialTest() {

        Response response =
                builders.RequestBuilder.getRequest()
                        //.get(Endpoints.TESTIMONIALS + "/" + CommonData.testimonialId)
                        .pathParam("id", CommonData.testimonialId)
                        .get(Endpoints.TESTIMONIALS + "/{id}")
                        .then()
                        .extract().response();

        Assert.assertEquals(response.getStatusCode(), 404);
    }
}











