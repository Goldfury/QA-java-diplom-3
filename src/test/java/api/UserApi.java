package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import dto.UserCreate;
import dto.UserLogIn;

import static io.restassured.RestAssured.given;

public class UserApi {


    @Step("Add baseUrl")
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Step("Отправка пост запроса на авторизацию пользователя и извлечением токена из ответа")
    public String sendPostRequestLogInUser(UserLogIn userLogIn) {
        setUp();
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(userLogIn)
                .when()
                .post("/api/auth/login");


        return response.then().extract().body().path("accessToken");
    }

    @Step("Запрос на удаления пользователя по его токену")
    public Response sendDeleteRequestUser(UserCreate userCreate) {
        setUp();
        return given()
                .header("Authorization", sendPostRequestLogInUser(new UserLogIn(userCreate.getEmail(), userCreate.getPassword())))
                .when()
                .delete("/api/auth/user");
    }

    @Step("Отправка пост запроса на создание пользователя")
    public Response sendPostRequestCreateUser(UserCreate userCreate) {
        setUp();
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(userCreate)
                .when()
                .post("/api/auth/register");
    }
}
