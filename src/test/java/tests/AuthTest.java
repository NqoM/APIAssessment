package tests;

import base.BaseTest;
import builders.PayloadBuilder;
import commons.CommonData;
import endpoints.Endpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import builders.RequestBuilder;

public class AuthTest extends BaseTest {

    @Test(priority = 1)
    public void adminLoginTest() {

        Response response =
                RequestBuilder.getRequestSpec()
                        .body(PayloadBuilder.loginPayload())
                        .log().all()
                        .post(Endpoints.LOGIN);

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        CommonData.token = response.jsonPath().getString("data.token");

        System.out.println(
                "TOKEN: " + CommonData.token);
    }
}