import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.ForgottenPasswordPage;
import pageObject.LogInPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;
import userDto.UserCreate;
import userDto.UserLogIn;


public class AuthTest {
    private DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;
    //Pages
    UserApi userApi;
    MainPage mainPage;
    LogInPage logInPage;
    RegistrationPage registrationPage;
    ForgottenPasswordPage forgottenPasswordPage;

    UserCreate userCreate = new UserCreate("testakyl@gmail.com", "password", "akyl");
    UserLogIn userLogIn = new UserLogIn(userCreate.getEmail(), userCreate.getPassword());

    @Before
    @Description("Создается вебдрайвер, также отправляется запрос на создание пользователя и инициация классов страниц")
    public void setUp() {
        driverFactory.initDriver();
        driver = driverFactory.getDriver();

        userApi = new UserApi();
        userApi.sendPostRequestCreateUser(userCreate);

        mainPage = new MainPage(driver);
        logInPage = new LogInPage(driver);
        registrationPage = new RegistrationPage(driver);
        forgottenPasswordPage = new ForgottenPasswordPage(driver);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Авторизация созданного юзера")
    public void test1() {
        mainPage.clickBtnEnterAccount();
        logInPage.setAuthUserData(userLogIn);
        logInPage.clickBtnLogin();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void test2() {
        mainPage.clickBtnPersonalAccount();
        assertTrue(logInPage.isEnterHeaderVisible());

    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void test3() {
        mainPage.clickBtnPersonalAccount();
        logInPage.clickBtnRegistration();
        registrationPage.clickEnterButton();
        assertTrue(logInPage.isEnterHeaderVisible());
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void test4() {
        mainPage.clickBtnPersonalAccount();
        logInPage.clickBtnRecoverPassword();
        forgottenPasswordPage.clickLogInBtn();
        assertTrue(logInPage.isEnterHeaderVisible());
    }

    @Step("AssertTrue")
    public void assertTrue(boolean bln) {
        Assert.assertTrue(bln);
    }

    @After
    public void tearDown() {
        driver.quit();
        userApi.sendDeleteRequestUser(userCreate).then().statusCode(202);
    }

}
