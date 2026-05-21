package builders;

import commons.CommonData;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class RequestBuilder {

    public static RequestSpecification getRequestSpec() {

        return given()
                .header("Content-Type", "application/json");
    }

    public static RequestSpecification getAuthorizedRequestSpec() {

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization",
                        "Bearer " + CommonData.token);
    }
}


//Reusable request specification.