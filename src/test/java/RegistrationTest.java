import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LogInPage;
import pages.MainPage;
import pages.RegistrationPage;
import dto.UserCreate;

public class RegistrationTest {
    private DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;

    private String url = "https://stellarburgers.nomoreparties.site/";
    UserCreate userCreate = new UserCreate("testakyl@gmail.com", "password", "akyl");
    UserCreate createIncrorrectUser = new UserCreate("testakyl@gmail.com", "pass", "akyl");

    RegistrationPage registrationPage;
    MainPage mainPage;
    LogInPage logInPage;


    @Before
    public void setUp() {
        driverFactory.initDriver();
        driver = driverFactory.getDriver();

        registrationPage = new RegistrationPage(driver);
        mainPage = new MainPage(driver);
        logInPage = new LogInPage(driver);

    }


    @Test
    @DisplayName("Регистриция юзера")
    @Description("Правильно заполненные данные, проверки на отображения UI не нужны, " +
            "так как стоит проверка на статус коде запроса, " +
            "если он отработал значит и аккаунт создался через UI")
    public void userRegistrationTest() {
        mainPage.clickBtnPersonalAccount();
        logInPage.clickBtnRegistration();
        registrationPage.setUserInfo(userCreate);
        compareDeleteStatusCode();
    }

    @Test
    @DisplayName("Некорректная регистриция юзера")
    @Description("Короткий пароль, стоит проверка на отображение алерта некорректного пароля")
    public void incorrectuserRegistrationTest() {
        mainPage.clickBtnPersonalAccount();
        logInPage.clickBtnRegistration();
        registrationPage.setUserInfo(createIncrorrectUser);
        registrationPage.incorrectPasswordTextIsDisplayed();
    }

    @Step("Проверка статуса кода удаления аккаунта")
    public void compareDeleteStatusCode() {
        UserApi userApi = new UserApi();
        userApi.sendDeleteRequestUser(userCreate).then().statusCode(202);
    }


    @After
    public void tearDown() {
        driver.quit();
    }


}
