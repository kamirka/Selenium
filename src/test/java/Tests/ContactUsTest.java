package Tests;

import enums.MessageSubject;
import model.Message;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ContactUsFormPage;
import pages.TopMenuPage;
import utils.PageTitleUtils;

import java.time.Duration;


import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactUsTest extends BaseTest {

    private TopMenuPage topMenuPage;
    private ContactUsFormPage contactUsFormPage;

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        System.out.println(driver.getTitle());
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        topMenuPage = new TopMenuPage(driver);
        contactUsFormPage = new ContactUsFormPage(driver);
    }

    @Test
    @Order(1)
    public void  shouldhNotAllowSendEmptyContactForm() {
        //----GLOBALNY WAIT DLA WSZYSTKICH ELEMENTÓW

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        topMenuPage.clickOnContactUsLink();
        contactUsFormPage.clickOnSendButton();
        contactUsFormPage.redAlertBoxIsDisplayed();
        assertThat(contactUsFormPage.redAlertBoxIsDisplayed()).isTrue();

        //--wait --nie używaj :)
        //Thread.sleep(2000);

        //Explicit wait
//        WebElement redAlert = driver.findElement(By.className("alert-danger"));
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.visibilityOf(redAlert));
//        assertThat(redAlert.isDisplayed()).isTrue();
    }

    @Test
    @Order(2)
    public void shouldNotAllowToSendContactUsFormOnlyWithEmail(){
        topMenuPage.clickOnContactUsLink();
        contactUsFormPage.fillEmail();
        contactUsFormPage.clickOnSendButton();
        contactUsFormPage.redAlertBoxIsDisplayed();
        assertThat(contactUsFormPage.redAlertBoxIsDisplayed()).isTrue();

        //Explicit wait
//        WebElement redAlert = driver.findElement(By.className("alert-danger"));
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.visibilityOf(redAlert));
//        assertThat(redAlert.isDisplayed()).isTrue();
    }

    @Test
    @Order(3)
    public void sendCorrectContactUsForm(){
        topMenuPage.clickOnContactUsLink();

        Message message = new Message();
        message.setSubjectHeading(MessageSubject.CUSTOMER_SERVICE);
        message.setEmail("test@test.com");
        message.setOrderReference("test");
        message.setMessage("test message");

        contactUsFormPage.sendCorrectContactUsMessage(message);

        assertThat(contactUsFormPage.greenAlertBoxIsDisplayed()).isTrue();
        assertThat(contactUsFormPage.getTextFromGreenAlertBox()).contains("Your message has been successfully sent to our team.");

//        //Explicit wait
//        WebElement greenAlert = driver.findElement(By.className("alert-success"));
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.visibilityOf(greenAlert));
//        assertThat(greenAlert.getText().contains("Your message has been successfully sent to our team."));
    }

}
