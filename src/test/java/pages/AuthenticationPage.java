package pages;

import model.CreateAccount.Address;
import model.CreateAccount.Email;
import model.CreateAccount.PersonalInformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.Country;

public class AuthenticationPage extends BasePage {

    public AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email_create")
    WebElement emailCreate;

    @FindBy(id = "SubmitCreate")
    WebElement submitButtonEmailCreate;

    @FindBy(id = "uniform-id_gender1")
    WebElement titleMr;

    @FindBy(id = "uniform-id_gender2")
    WebElement titleMrs;

    @FindBy(id = "customer_firstname")
    WebElement piFirstName;

    @FindBy(id = "customer_lastname")
    WebElement piLastName;

    @FindBy(id = "passwd")
    WebElement piPassword;

    @FindBy(id = "days")
    WebElement piDateOfBirthDays;

    @FindBy(id = "months")
    WebElement piDateOfBirthMonths;

    @FindBy(id = "years")
    WebElement piDateOfBirthYears;

    @FindBy(id = "firstname")
    WebElement aFirstName;

    @FindBy(id = "lastname")
    WebElement aLastName;

    @FindBy(id = "company")
    WebElement aCompany;

    @FindBy(id = "address1")
    WebElement aAddress1;

    @FindBy(id = "address2")
    WebElement aAddress2;

    @FindBy(id = "city")
    WebElement aCity;

    @FindBy(id = "id_state")
    WebElement aState;

    @FindBy(id = "postcode")
    WebElement aPostalCode;

    @FindBy(id = "id_country")
    WebElement aCountry;

    @FindBy(id = "other")
    WebElement aAdditionalInfo;

    @FindBy(id = "phone_mobile")
    WebElement aMobilePhone;

    @FindBy(id = "alias")
    WebElement aAddressAlias;

    @FindBy(id = "submitAccount")
    WebElement submitAccount;


    public void checkEmailAvailability(Email email) {

        emailCreate.sendKeys(email.getEmail());
        submitButtonEmailCreate.click();
    }

    public void createAccount(PersonalInformation pi, Address address) {
        Select select = new Select(aState);
        Select selectCountry = new Select(aCountry);
        Select selectD = new Select(piDateOfBirthDays);
        Select selectM = new Select(piDateOfBirthMonths);
        Select selectY = new Select(piDateOfBirthYears);

        //Filling Personal Information
        titleMrs.click();
        piFirstName.sendKeys(pi.getFirstName());
        piLastName.sendKeys(pi.getLastName());
        piPassword.sendKeys(pi.getPassword());
        selectD.selectByIndex((int) (Math.random() * 25 + 1));
        selectM.selectByIndex((int) (Math.random() * 12 + 1));
        selectY.selectByValue(String.valueOf((int) (Math.random() * (2000 - 1950 + 1) + 1950)));

        //Filling Address Information
        aFirstName.sendKeys(address.getFirstName());
        aLastName.sendKeys(address.getLastName());
        aCompany.sendKeys(address.getCompanyName());
        aAddress1.sendKeys(address.getAddress1Name());
        aCity.sendKeys(address.getCity());
        select.selectByIndex((int) (Math.random() * 50 + 1));
        aPostalCode.sendKeys(address.getPostalCode());
        selectCountry.selectByVisibleText(Country.COUNTRY);
        aAdditionalInfo.sendKeys(address.getAdditionalInformation());
        aMobilePhone.sendKeys(address.getMobilePhone());
        aAddressAlias.sendKeys(address.getAddressAlias());

        submitAccount.click();
    }
}
