package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import dto.UserCreate;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;

    private By registrationButton = By.xpath(".//*[text() = 'Зарегистрироваться']");
    private By name = By.xpath(".//*[text()='Имя']/..//input");
    private By email = By.xpath(".//*[text()='Email']/..//input");
    private By password = By.xpath(".//*[text()='Пароль']/..//input");
    private By incorrectPasswordText = By.xpath(".//*[text()='Некорректный пароль']");
    private By enterButton = By.xpath(".//*[text()='Войти']");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение полей регистрации")
    public void setUserInfo(UserCreate userCreate){
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.elementToBeClickable(driver.findElement(name)));

        driver.findElement(name).sendKeys(userCreate.getName());
        driver.findElement(email).sendKeys(userCreate.getEmail());
        driver.findElement(password).sendKeys(userCreate.getPassword());
        driver.findElement(registrationButton).click();
    }

    @Step("Отображение алерта что пароль некорректный")
    public boolean incorrectPasswordTextIsDisplayed(){
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.elementToBeClickable(driver.findElement(incorrectPasswordText)));

        return driver.findElement(incorrectPasswordText).isDisplayed();
    }

    @Step("Нажатие по кнопке войти на странице регистрации")
    public void clickEnterButton(){
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.elementToBeClickable(driver.findElement(enterButton)));
        driver.findElement(enterButton).click();
    }

}

