import io.restassured.response.Response;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Profile {
    public Profile(String name, String about) {
        this.name = name;
        this.about = about;
    }

    public Profile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    private String name;
    private String about;

    //Card card = new Card("Василий Васильев","Самый крутой исследователь");
    @Test
    public void updateProfileAndCheckStatusCode() {
        Profile profile = new Profile("Василий Васильев", "Самый крутой исследователь");
        given()
                .header("Content-type", "application/json")
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzA1MzhkMDA4ZDQzNDAwM2QzM2UwNzUiLCJpYXQiOjE2NjU0MDA2ODMsImV4cCI6MTY2NjAwNTQ4M30.Int9CQko28mDuEhbAxqTqMWFOmumvDosShAd2VqIl9M")
                .and()
                .body(profile)
                .when()
                .patch("/api/users/me");

    }
}