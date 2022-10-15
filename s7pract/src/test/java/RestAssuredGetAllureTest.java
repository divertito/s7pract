import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredGetAllureTest {

    String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzA1MzhkMDA4ZDQzNDAwM2QzM2UwNzUiLCJpYXQiOjE2NjU0MDA2ODMsImV4cCI6MTY2NjAwNTQ4M30.Int9CQko28mDuEhbAxqTqMWFOmumvDosShAd2VqIl9M";

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check status code of /users/me")
    @Description("Basic test for /users/me endpoint")
    public void getMyInfoStatusCode() {
        given()
                .auth().oauth2(bearerToken)
                .get("/api/users/me")
                .then().statusCode(200);
    }

    @Test
    @DisplayName("Check user name")
    @Description("Checking user name is very important")
    public void checkUserName() {
        given()
                .auth().oauth2(bearerToken)
                .get("/api/users/me")
                .then().assertThat().body("data.name", equalTo("Василий Васильев"));
    }

    @Test
    @DisplayName("Check user name and print response body")
    @Description("This is a more complicated test with console output")
    public void checkUserNameAndPrintResponseBody() {
        Response response = sendGetRequestUsersMe();
        compareUserNameToText(response, "Василий Васильев1");
        printResponseBodyToConsole(response); // вывели тело ответа на экран
    }

    // метод для шага "Отправить запрос":
    @Step("Send GET request to /api/users/me")
    public Response sendGetRequestUsersMe() {
        Response response = given().auth().oauth2(bearerToken).get("/api/users/me");
        return response;
    }

    // метод для шага "Сравнить имя пользователя с заданным":
    @Step("Compare user name to something")
    public void compareUserNameToText(Response response, String username) {
        response.then().assertThat().body("data.name", equalTo(username));
    }

    // метод для шага "Вывести тело ответа в консоль":
    @Step("Print response body to console")
    public void printResponseBodyToConsole(Response response) {
        System.out.println(response.body().asString());
    }
}
    