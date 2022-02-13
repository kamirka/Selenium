package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;


public class BaseTest {
    protected static final String BASE_URL="http://automationpractice.com/index.php";

    protected WebDriver driver; //do komunikacji z przeglądarką

    @BeforeAll //przed wszystkimi testami
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
        //po każdym teście
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
