package builders;

import commons.CommonData;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestBuilder {

    public static RequestSpecification getRequest() {

        RequestSpecification spec = given()
                .header("Content-Type", "application/json");

        if (CommonData.token != null) {
            spec.header("Authorization", "Bearer " + CommonData.token);
        }

        return spec;
    }
}