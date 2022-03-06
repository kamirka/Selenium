package pages;

import model.Message;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ContactUsFormPage extends BasePage {

    public ContactUsFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    WebElement email;

    @FindBy(xpath = "//*[@id='id_contact']")
    WebElement subjectHeading;

    @FindBy(id = "id_order")
    WebElement orderReference;

    @FindBy(id = "message")
    WebElement messageTextArea;

    @FindBy(id = "submitMessage")
    WebElement sendButton;

    @FindBy(className = "alert-danger")
    WebElement redAlertBox;

    @FindBy(className = "alert-success")
    WebElement greenAlertBox;

    public void fillEmail() {
        email.sendKeys("test@test.com");
    }

    public void clickOnSendButton() {
        sendButton.click();
    }

    public boolean redAlertBoxIsDisplayed() {
        return isAlertBoxDisplayed(redAlertBox);
    }

    public boolean greenAlertBoxIsDisplayed() {
        return isAlertBoxDisplayed(greenAlertBox);
    }

    private boolean isAlertBoxDisplayed(WebElement box) {
        wait.until(ExpectedConditions.visibilityOf(box));

        boolean isDisplayed = false;
        try {
            isDisplayed = box.isDisplayed();
        } catch (NoSuchElementException e) {
        }
        return isDisplayed;
    }

    public String getTextFromGreenAlertBox() {
        wait.until(ExpectedConditions.visibilityOf(greenAlertBox));
        return greenAlertBox.getText();
    }

    public void sendCorrectContactUsMessage(Message message) {
        Select select = new Select(subjectHeading);
        select.selectByVisibleText(message.getSubjectHeading().getValue());

        email.sendKeys(message.getEmail());
        orderReference.sendKeys(message.getMessage());
        messageTextArea.sendKeys(message.getMessage());
        sendButton.click();
    }
}
