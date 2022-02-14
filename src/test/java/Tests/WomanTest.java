package Tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.WomenPage;
import utils.PageTitleUtils;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WomanTest extends BaseTest{
    private WomenPage womanPage;

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        System.out.println(driver.getTitle());
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        womanPage = new WomenPage(driver);
    }

    @Test
    @Order(1)
    public void  showPricesBiggerThanZero(){

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        womanPage.clickOnWomenLink();
        womanPage.getWomenProductPrice();

        List<String> productPrices = womanPage.getWomenProductPrice();

        //Removing $ sign from list
        List<String> sProductPricesList = productPrices.stream()
                .map(s -> s.replaceAll("[$]", ""))
                .collect(Collectors.toList());
        System.out.println(sProductPricesList);

        //Converting to Float list and filtering prices > 0
        List<Float> productPriceList = sProductPricesList.stream()
                        .map(Float::valueOf)
                        .filter(f->f.floatValue() > 0)
                        .collect(Collectors.toList());
        System.out.println(productPriceList);

        assertThat(productPriceList).isNotEmpty();
   }
}
