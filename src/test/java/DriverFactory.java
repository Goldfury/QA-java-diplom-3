import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

@Getter
public class DriverFactory {
    private WebDriver driver;
    private String url = "https://stellarburgers.nomoreparties.site/";

    public void initDriver() {
        if ("yandex".equals(System.getProperty("browser")))
            startYandex();
        else
            startChrome();

        driver.get(url);
    }


    public void startChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }

    public void startYandex() {
        WebDriverManager.chromedriver().driverVersion(System.getProperty("driver.version")).setup();
        var options = new ChromeOptions();
        options.setBinary(System.getProperty("webdriver.yandex.bin"));
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }


}
