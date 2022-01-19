package eu.pierrebeitz;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GhRateResourceTest {

    @Test
    public void testRateEndpoint() {
        given()
          .when().get("/rate")
          .then()
             .statusCode(200);
    }

}