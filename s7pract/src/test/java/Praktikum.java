import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Praktikum {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }
    @Test
    public void createNewPlaceAndCheckResponse(){
        File json = new File("src/test/resources/newCard.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzA1MzhkMDA4ZDQzNDAwM2QzM2UwNzUiLCJpYXQiOjE2NjU0MDA2ODMsImV4cCI6MTY2NjAwNTQ4M30.Int9CQko28mDuEhbAxqTqMWFOmumvDosShAd2VqIl9M")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/cards");
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
    }
    @Test
    public void updateProfileAndCheckStatusCode() {
        File json = new File("src/test/resources/updateProfile.json"); // запиши файл в файловую переменную
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzA1MzhkMDA4ZDQzNDAwM2QzM2UwNzUiLCJpYXQiOjE2NjU0MDA2ODMsImV4cCI6MTY2NjAwNTQ4M30.Int9CQko28mDuEhbAxqTqMWFOmumvDosShAd2VqIl9M")
                        .and()
                        .body(json)
                        .when()
                        .patch("/api/users/me");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("data.name",notNullValue());
    }
    @Test
    public void checkUserName() {
        User user = given()
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzA1MzhkMDA4ZDQzNDAwM2QzM2UwNzUiLCJpYXQiOjE2NjU0MDA2ODMsImV4cCI6MTY2NjAwNTQ4M30.Int9CQko28mDuEhbAxqTqMWFOmumvDosShAd2VqIl9M")
                .get("/api/users/me")
       .body().as(User.class); // напиши код для десериализации ответа в объект типа User
       // выбери подходящий assert
                MatcherAssert.assertThat(user, notNullValue());
    }
}