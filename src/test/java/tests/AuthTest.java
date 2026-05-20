package tests;

import base.BaseTest;
import commons.CommonData;
import endpoints.Endpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import builders.RequestBuilder;

public class AuthTest extends BaseTest {

    @Test
    public void adminLoginTest() {

        String payload = "{\n" +
                "  \"email\": \"admin@gmail.com\",\n" +
                "  \"password\": \"@12345678\"\n" +
                "}";

        Response response =
                RequestBuilder.getRequest()
                        .body(payload)
                        .post(Endpoints.LOGIN)
                        .then()
                        .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);

        CommonData.token = response.jsonPath().getString("data.token");

        System.out.println("TOKEN: " + CommonData.token);
    }
}