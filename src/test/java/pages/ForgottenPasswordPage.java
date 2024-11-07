package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgottenPasswordPage {
    private WebDriver driver;

    private By buttonLogin = By.xpath(".//a[text() = 'Войти']");

    public ForgottenPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход по кнопке войти")
    public void clickLogInBtn() {
        driver.findElement(this.buttonLogin).click();
    }
}
