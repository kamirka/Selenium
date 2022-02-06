import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTest {
    private WebDriver driver; //do komunikacji z przeglądarką
    private WebElement element; //obsługa elementów na stronie

    @BeforeAll //przed wszystkimi testami
   public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach //przed każdym testem
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach //po każdym teście
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldReturnCorrectPageTitle() {
        driver.get("http://automationpractice.com/index.php");
        System.out.println(driver.getTitle());
        assertThat(driver.getTitle()).isEqualTo("My Store");
    }

    @Test
    public void shouldSeePopularItemsOnHomePage(){
        driver.get("http://automationpractice.com/index.php");
        List<WebElement> productNames = driver.findElements(By.cssSelector("#homefeatured .product-name"));
        List<WebElement> productNamesXPath = driver.findElements(By.xpath("//*[@id='homefeatured']//*[@class='product-name']"));


        for (WebElement productName: productNames
             ) {
            System.out.println(productName.getText());
        }

        for (WebElement productName: productNamesXPath
        ) {
            System.out.println(productName.getText());
        }

//        boolean anyProductHAsEmptyName = productNames.stream()
//                .anyMatch(c -> c.getText().isEmpty());
//
//        Assertions.assertThat(anyProductHAsEmptyName).isFalse();

        List<WebElement> productsWithEmptyNames = productNames.stream()
                .filter(c -> c.getText().isEmpty())
                .collect(Collectors.toList());

        assertThat(productsWithEmptyNames).isEmpty();
    
    }

}
