package tests;


import base.BaseTest;
import builders.PayloadBuilder;
import builders.PayloadBuilder;
import builders.RequestBuilder;
import commons.CommonData;
import endpoints.Endpoints;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestimonialTests extends BaseTest {

    @Test(dependsOnMethods = "tests.AuthTest.adminLoginTest")
    public void createTestimonialTest() {

        Response response =
                RequestBuilder.getAuthorizedRequestSpec()
                        .body(PayloadBuilder.createTestimonialPayload())
                        .log().all()
                        .post(Endpoints.TESTIMONIALS);

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 201);

        CommonData.testimonialId =
                response.jsonPath().getString("data.Id");

        System.out.println(
                "ID: " + CommonData.testimonialId);
    }

    @Test(priority = 3)
    public void updateTestimonialTest() {

        Response response =
                RequestBuilder.getAuthorizedRequestSpec()
                        .body(PayloadBuilder.updateTestimonialPayload())
                        .log().all()
                        .put(Endpoints.TESTIMONIALS + "/" + CommonData.testimonialId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void deleteTestimonialTest() {

        Response response =
                RequestBuilder.getAuthorizedRequestSpec()
                        .log().all()
                        .delete(Endpoints.TESTIMONIALS + "/" + CommonData.testimonialId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5)
    public void verifyDeletedTestimonialTest() {

        Response response =
                RequestBuilder.getRequestSpec()
                        .log().all()
                        .get(Endpoints.TESTIMONIALS + "/" + CommonData.testimonialId);

        Assert.assertEquals(response.getStatusCode(), 404);
    }
}











