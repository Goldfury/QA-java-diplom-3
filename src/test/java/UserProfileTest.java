import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import dto.UserCreate;
import dto.UserLogIn;

public class UserProfileTest {
    private DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;

    UserApi userApi;
    MainPage mainPage;
    LogInPage logInPage;
    UserProfilePage userProfilePage;

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
        userProfilePage = new UserProfilePage(driver);
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет")
    @Description("Авторизация созданного юзера, при повторном нажатии ЛК находит кнопку профиль")
    public void userProfileCheckTest() {
        mainPage.clickBtnEnterAccount();
        logInPage.setAuthUserData(userLogIn);
        logInPage.clickBtnLogin();
        mainPage.clickBtnPersonalAccount();
        userProfilePage.isProfileButton();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    @Description("Авторизация созданного юзера, при повторном нажатии ЛК переходит в профиль, " +
            "там же в хедере жмет на кнопку ")
    public void userProfileClickConstructorTest() {
        mainPage.clickBtnEnterAccount();
        logInPage.setAuthUserData(userLogIn);
        logInPage.clickBtnLogin();
        mainPage.clickBtnPersonalAccount();
        userProfilePage.clickConstructorBtn();
        mainPage.isBurgerConstructorBunsVisible();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    @Description("Авторизация созданного юзера, при повторном нажатии ЛК переходит в профиль," +
            "там же вхедере нажимает на лого")
    public void userProfileClickLogoTest() {
        mainPage.clickBtnEnterAccount();
        logInPage.setAuthUserData(userLogIn);
        logInPage.clickBtnLogin();
        mainPage.clickBtnPersonalAccount();
        userProfilePage.clickLogo();
        mainPage.isBurgerConstructorBunsVisible();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    @Description("Авторизация созданного юзера, при повторном нажатии ЛК переходит в профиль," +
            "там же вхедере нажимает на лого")
    public void userLogOutTest() {
        mainPage.clickBtnEnterAccount();
        logInPage.setAuthUserData(userLogIn);
        logInPage.clickBtnLogin();
        mainPage.clickBtnPersonalAccount();
        userProfilePage.clickExitBtn();
        logInPage.isEnterHeaderVisible();
    }


    @After
    @Description("Закрывает драйвер и удаляет аккаунт")
    public void tearDown() {
        driver.quit();
        userApi.sendDeleteRequestUser(userCreate).then().statusCode(202);
    }

}
