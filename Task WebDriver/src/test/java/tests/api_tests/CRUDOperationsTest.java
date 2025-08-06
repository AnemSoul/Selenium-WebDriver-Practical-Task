package tests.api_tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CRUDOperationsTest {
  private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";
  private static final String USER_ID_URL = BASE_URL + "/1";

  @Test(priority = 1)
  public void testCreateUser() {
    SoftAssert softAssert = new SoftAssert();

    // Тело запроса
    String requestBody = """
                {
                  "name": "John Doe",
                  "username": "johndoe",
                  "email": "johndoe@example.com"
                }
                """;

    var response = RestAssured
        .given()
        .header("Content-Type", "application/json")
        .body(requestBody)
        .when()
        .post(BASE_URL)
        .then()
        .statusCode(201)
        .extract()
        .response();

    softAssert.assertEquals(
        response
            .jsonPath()
            .getString("name"), "John Doe",
        "Field 'name' does not match.");
    softAssert.assertEquals(
        response
            .jsonPath()
            .getString("username"), "johndoe",
        "Field 'username' does not match.");
    softAssert.assertEquals(
        response
            .jsonPath()
            .getString("email"), "johndoe@example.com",
        "Field 'email' does not match.");

    softAssert.assertAll();
  }

  @Test(priority = 2, dependsOnMethods = "testCreateUser")
  public void testReadUser() {
    String user = RestAssured
        .given()
        .when()
        .get(USER_ID_URL)
        .then()
        .extract()
        .asString();

    assertNotNull(user, "User not found!");
  }

  @Test(priority = 3, dependsOnMethods = "testReadUser")
  public void testUpdateUser() {

    String requestBody = """
                {
                  "name": "Jane Doe"
                }
                """;

    var response = RestAssured
        .given()
        .header("Content-Type", "application/json")
        .body(requestBody)
        .when()
        .put(USER_ID_URL)
        .then()
        .statusCode(200)
        .extract()
        .response();

    assertEquals(
        response
            .jsonPath()
            .getString("name"), "Jane Doe",
        "Field 'name' was not updated correctly.");
  }

  @Test(priority = 4)
  public void testDeleteUser() {
    int statusCode = RestAssured
        .given()
        .when()
        .delete(USER_ID_URL)
        .then()
        .extract()
        .statusCode();

    assertEquals(statusCode, 200, "Error deleting user.");
  }
}
