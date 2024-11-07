import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class BurgerConstructorTest {

    private DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;
    //Pages
    MainPage mainPage;

    @Before
    @Description("Создается вебдрайвер, также отправляется запрос на создание пользователя и инициация классов страниц")
    public void setUp() {
        driverFactory.initDriver();
        driver = driverFactory.getDriver();

        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Переходы к разделу Булки")
    @Description("Сначала кликает по Соусу чтобы кнопка нажатия на булочек была рабочей")
    public void checkBunsSection(){
        mainPage.clickSauces();
        mainPage.clickBuns();
        mainPage.isTextBuns();

    }

    @Test
    @DisplayName("Переходы к разделу Соусы")
    public void checkSaucesSection(){
        mainPage.clickSauces();
        mainPage.isTextSauces();

    }

    @Test
    @DisplayName("Переходы к разделу Начинки")
    public void checkFillingsSection(){
        mainPage.clickFillings();
        mainPage.isTextFillings();
    }

    @After
    public void tearDown(){
//        driver.quit();
    }
}
