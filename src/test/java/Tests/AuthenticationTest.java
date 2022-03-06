package Tests;

import com.github.javafaker.Faker;
import model.CreateAccount.Address;
import model.CreateAccount.Email;
import model.CreateAccount.PersonalInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AuthenticationPage;
import pages.MyAccountPage;
import pages.TopMenuPage;
import utils.PageTitleUtils;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationTest extends BaseTest {

    private AuthenticationPage authenticationPage;
    private TopMenuPage topMenuPage;
    private MyAccountPage myAccountPage;

    Faker faker = new Faker();

    String firstNameF = faker.name().firstName();
    String lastNameF = faker.name().lastName();
    String companyNameF = faker.company().name();
    String emailF = (firstNameF + "@" + lastNameF + ".pl");
    String address1NameF = faker.address().fullAddress();
    String passwordF = (faker.lorem().characters(4, 10) + faker.number().randomDigit());
    String cityF = faker.address().cityName();
    String postalCodeF = faker.number().digits(5);
    String mobilePhoneF = faker.phoneNumber().cellPhone();
    String addressAliasF = faker.name().username();
    String additionalInfo = faker.lorem().sentence(6);


    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        System.out.println(driver.getTitle());
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        authenticationPage = new AuthenticationPage(driver);
        topMenuPage = new TopMenuPage(driver);
        myAccountPage = new MyAccountPage(driver);

    }

    @Test
    @Order(1)
    public void sendCorrectContactUsForm() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        topMenuPage.clickOnSignInLink();

        Email email = new Email();
        email.setEmail(emailF);
        authenticationPage.checkEmailAvailability(email);

        PersonalInformation personalInformation = new PersonalInformation();

        personalInformation.setFirstName(firstNameF);
        personalInformation.setLastName(lastNameF);
        personalInformation.setPassword(passwordF);
        personalInformation.setDateOfBirthDays(personalInformation.getDateOfBirthDays());
        personalInformation.setDateOfBirthMonths(personalInformation.getDateOfBirthMonths());
        personalInformation.setDateOfBirthYears(personalInformation.getDateOfBirthYears());


        Address address = new Address();
        address.setFirstName(firstNameF);
        address.setLastName(lastNameF);
        address.setCompanyName(companyNameF);
        address.setAddress1Name(address1NameF);
        address.setCity(cityF);
        address.setPostalCode(postalCodeF);
        address.setAdditionalInformation(additionalInfo);
        address.setMobilePhone(mobilePhoneF);
        address.setAddressAlias(addressAliasF);


        authenticationPage.createAccount(personalInformation, address);

        assertThat(myAccountPage.infoParagraph()).isTrue();
        assertThat(myAccountPage.getTextFromParagraph()).contains("Welcome to your account. Here you can manage all of your personal information and orders.");

    }
}
