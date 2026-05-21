package builders;

public class PayloadBuilder {

    // LOGIN
    public static String loginPayload() {

        return "{\n" +
                "  \"email\": \"admin@gmail.com\",\n" +
                "  \"password\": \"@12345678\"\n" +
                "}";
    }

    // CREATE TESTIMONIAL
    public static String createTestimonialPayload() {

        return "{\n" +
                "  \"title\": \"Great Course\",\n" +
                "  \"content\": \"I am enjoying the course\",\n" +
                "  \"rating\": 5,\n" +
                "  \"isPublic\": true\n" +
                "}";
    }

    // UPDATE TESTIMONIAL
    public static String updateTestimonialPayload() {

        return "{\n" +
                "  \"title\": \"Updated Title\",\n" +
                "  \"content\": \"Updated content\",\n" +
                "  \"rating\": 4\n" +
                "}";
    }
}

