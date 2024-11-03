package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import userDto.UserLogIn;

import java.time.Duration;

public class LogInPage {
    private WebDriver driver;

    private By buttonRegistration = By.xpath(".//*[text() = 'Зарегистрироваться']");
    private By buttonLogin = By.xpath(".//button[text() = 'Войти']");
    private By buttonRecoverPassword = By.xpath(".//*[text()='Восстановить пароль']");
    private By email = By.name("name");
    private By password = By.name("Пароль");
    private By headerLogin = By.xpath(".//*[text() = 'Вход']");

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку регистрации")
    public void clickBtnRegistration() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(driver.findElement(buttonRegistration)));
        driver.findElement(buttonRegistration).click();
    }

    @Step("Заполнение полей авторизации после обнаружения текста вход над обьектом")
    public void setAuthUserData(UserLogIn user) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(driver.findElement(headerLogin)));
        driver.findElement(email).sendKeys(user.getEmail());
        driver.findElement(password).sendKeys(user.getPassword());
    }


    @Step("Проверка отображение окна с текстом Вход")
    public boolean isEnterHeaderVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.visibilityOfElementLocated(headerLogin));

        return driver.findElement(headerLogin).isDisplayed();
    }

    @Step("Нажатие на кнопку войти на странице авторизации")
    public void clickBtnLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.elementToBeClickable(buttonLogin));

        driver.findElement(buttonLogin).click();
    }

    @Step("Нажать на кнопку восстановить пароль")
    public void clickBtnRecoverPassword() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.elementToBeClickable(buttonRecoverPassword));

        driver.findElement(buttonRecoverPassword).click();
    }

}
