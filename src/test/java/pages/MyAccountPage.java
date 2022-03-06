package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "info-account")
    WebElement h1;

    public boolean infoParagraph() {
        return isInfoParagraphDisplayed(h1);
    }

    public boolean isInfoParagraphDisplayed(WebElement h1) {
        wait.until(ExpectedConditions.visibilityOf(h1));

        boolean isDisplayed = false;
        try {
            isDisplayed = h1.isDisplayed();
        } catch (NoSuchElementException e) {
        }
        return isDisplayed;
    }

    public String getTextFromParagraph() {
        wait.until(ExpectedConditions.visibilityOf(h1));
        return h1.getText();
    }


}
