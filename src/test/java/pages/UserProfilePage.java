package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserProfilePage {
    private WebDriver driver;

    private By buttonProfile = By.xpath(".//a[text() = 'Профиль']");
    private By buttonConstructor = By.xpath(".//p[text()='Конструктор']");
    private By burgersLogo = By.className("AppHeader_header__logo__2D0X2");
    private By buttonExit = By.xpath(".//button[text() = 'Выход']");

    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Отображение профиля в лк")
    public boolean isProfileButton() {
        new WebDriverWait(driver, Duration.ofSeconds(45)).
                until(ExpectedConditions.visibilityOfElementLocated(buttonProfile));
        return driver.findElement(buttonProfile).isDisplayed();
    }

    //Клик по кнопке Конструктор
    public void clickConstructorBtn() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.visibilityOfElementLocated(buttonConstructor));
        driver.findElement(buttonConstructor).click();
    }

    //Клик по логотипу
    public void clickLogo() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.visibilityOfElementLocated(burgersLogo));
        driver.findElement(burgersLogo).click();
    }

    //Клик по кнопке Выход
    public void clickExitBtn() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.visibilityOfElementLocated(buttonExit));
        driver.findElement(buttonExit).click();
    }
}
