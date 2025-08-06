package tests.api_tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StatusCodeTest {

  private static final String URL = "https://jsonplaceholder.typicode.com/users";

  @Test
  public void testHttpStatusCode() {
    int statusCode = RestAssured
        .given()
        .when()
        .get(URL)
        .then()
        .extract()
        .statusCode();

    assertEquals(statusCode, 200, "Expected status code 200 but doesn't match.");
  }
}
