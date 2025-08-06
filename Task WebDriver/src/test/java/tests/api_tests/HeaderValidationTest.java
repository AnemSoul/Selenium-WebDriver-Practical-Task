package tests.api_tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HeaderValidationTest {

  private static final String URL = "https://jsonplaceholder.typicode.com/users";

  @Test
  public void testContentTypeHeaderExistsAndCorrect() {
    SoftAssert softAssert = new SoftAssert();

    String contentType = RestAssured
        .given()
        .when()
        .get(URL)
        .then()
        .extract()
        .header("Content-Type");

    softAssert.assertNotNull(contentType, "Content-Type header not found.");

    softAssert.assertEquals(contentType, "application/json; charset=utf-8",
        "The Content-Type header value does not match the expected value.");

    softAssert.assertAll();
  }
}
