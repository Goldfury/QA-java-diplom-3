package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    private final By btnPersonalAccount = By.xpath(".//*[text() = 'Личный Кабинет']");
    private final By buttonEnterAccount = By.xpath(".//*[text() = 'Войти в аккаунт']");
    private final By createBurgerText = By.xpath(".//*[text() = 'Соберите бургер']");

    private final By sectionBuns = By.xpath(".//span[contains(text(), 'Булки')]");
    private final By sectionSauces = By.xpath(".//span[contains(text(), 'Соусы')]");
    private final By sectionFillings = By.xpath(".//span[contains(text(), 'Начинки')]");

    private final By headerBuns = By.xpath(".//h2[contains(text(), 'Начинки')]");
    private final By headerSauces = By.xpath(".//h2[contains(text(), 'Соусы')]");
    private final By headerFillings = By.xpath(".//h2[contains(text(), 'Начинки')]");



    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать на кнопку Личный кабинет")
    public void clickBtnPersonalAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(btnPersonalAccount));
        driver.findElement(btnPersonalAccount).click();
    }

    @Step("Нажать на кнопку Войти в аккаунт")
    public void clickBtnEnterAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable((buttonEnterAccount)));
        driver.findElement(buttonEnterAccount).click();
    }

    @Step("Отображение текста Булки в раздели Булки")
    public boolean isBurgerConstructorBunsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(createBurgerText));
        return driver.findElement(createBurgerText).isDisplayed();
    }

    @Step("Нажать на кнопку Булки")
    public void clickBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(sectionBuns));
        driver.findElement(sectionBuns).click();
    }

    @Step("Отображение текста Булки в раздели Булки")
    public boolean isTextBuns() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        WebElement element = driver.findElement(headerBuns);
        wait.until(ExpectedConditions.visibilityOfElementLocated(headerBuns));

        return element.isDisplayed() && element.getAttribute("class").contains("active");

    }

    @Step("Нажать на кнопку Соусы")
    public void clickSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(sectionSauces));
        driver.findElement(sectionSauces).click();
    }

    @Step("Отображение текста Соусы в раздели Соусы")
    public boolean isTextSauces() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(headerSauces));

        WebElement element = driver.findElement(headerSauces);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        wait.until(ExpectedConditions.elementToBeClickable(element));

        return element.isDisplayed() && element.getAttribute("class").contains("active");

    }

    @Step("Нажать на кнопку Начинки")
    public void clickFillings() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(sectionFillings));
        driver.findElement(sectionFillings).click();
    }


    @Step("Отображение текста Начинки в раздели Начинки")
    public boolean isTextFillings() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(headerFillings));

        WebElement element = driver.findElement(headerFillings);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        wait.until(ExpectedConditions.elementToBeClickable(element));

        return element.isDisplayed() && element.getAttribute("class").contains("active");
    }
}
