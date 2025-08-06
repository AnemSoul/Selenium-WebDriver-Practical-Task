package tests.api_tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ResponseBodyValidationTest {

  private static final String URL = "https://jsonplaceholder.typicode.com/users";
  @Test
  public void testResponseBodyArraySize() {
    List<?> users = RestAssured
        .given()
        .when()
        .get(URL)
        .then()
        .extract()
        .jsonPath()
        .getList("$");

    assertEquals(users.size(), 10,
        "The response body does not contain 10 objects.");
  }
}
