package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {

        RestAssured.baseURI =
                "https://www.ndosiautomation.co.za/APIDEV";
    }
}

//Handles the base URI setup.