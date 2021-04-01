package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.*;

public class CrudApiTest {
    public static class StatusCode {
        public static final int CREATED = 201;
    }

    private static final String URL = "https://crudcrud.com/api/65e473a88855451fba98dc24dec49bd7";
    public static final String USERS = URL + "/users";
    public static final String GET_USERS = USERS + "/";


    @Test
    public void shouldPostUser() {
        User user = new User("Anna", "Kot", 10, "Plock", "Ruda", "5555555");

        Response response = given().
                contentType(ContentType.JSON).
                body(user)
                .when().post(USERS);

        response.prettyPrint();

        response
                .then()
                .statusCode(StatusCode.CREATED);
    }

    @Test
    public void shouldPostUsers() {
        User user1 = new User("Anna", "Kot", 10, "Plock", "Ruda", "5555555");
        User user2 = new User("GFGFGF", "Kot", 10, "Plock", "Ruda", "5555555");
        User user3 = new User("IUYIUY", "Kot", 10, "Plock", "Ruda", "5555555");

        Response response = given().
                contentType(ContentType.JSON).
                body(user1)
                .when().post(USERS);

        given().contentType(ContentType.JSON).body(user2).when().post(USERS);
        given().contentType(ContentType.JSON).body(user3).when().post(USERS);

        response.prettyPrint();

        response
                .then()
                .statusCode(StatusCode.CREATED);
    }


    @Test
    public void shouldGetUser() {
        String GET_USER = USERS + "/5fa654e5d899cd03e8b4f76e";
        User user = when().get(GET_USER).then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(User.class);

        assertThat(user.getName()).isEqualTo("Anna");

        System.out.println(user);
    }

    @Test
    public void shouldGetAllUsers() {
        String GET_USERS = USERS;

        User[] users = when().get(USERS).then().assertThat().statusCode(HttpStatus.SC_OK).extract().as(User[].class);

        System.out.println(Arrays.toString(users));
        assertThat(users[0].getName()).isEqualTo("Anna");
        assertThat(users[1].getName()).isEqualTo("GFGFGF");
        assertThat(users[2].getName()).isEqualTo("IUYIUY");

    }

    @Test
    public void shouldUpdateUser() {
        User user = new User("Jan", "Kot", 10, "Plock", "Ruda", "5555555");

        String PUT_USER = USERS + "/5fa654e5d899cd03e8b4f76e";

        given().contentType(ContentType.JSON)
                .body(user)
                .when().put(PUT_USER)
                .then()
                .statusCode(HttpStatus.SC_OK);

        User user1 = when().get(PUT_USER).then().extract().as(User.class);
        assertThat(user1.getName()).isEqualTo("Jan");
    }

    @Test
    public void shouldDeleteUser() {
        String DELETE_USER = USERS + "/5fa71e70d899cd03e8b4f79b";
        when().delete(DELETE_USER).then().statusCode(HttpStatus.SC_OK);
    }
}
