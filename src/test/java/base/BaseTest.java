package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected static final String BASE_URL = "https://www.ndosiautomation.co.za/APIDEV";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}