package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class WomenPage extends BasePage{
    public WomenPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Women")
    WebElement womenLink;

    @FindBy(xpath = "//*[@class='right-block']//*[@class='content_price']//*[@itemprop='price']")
    List<WebElement> womenPrices;

    public void clickOnWomenLink(){
        womenLink.click();
    }

    public List<String> getWomenProductPrice(){
        return womenPrices.stream()
                .map(p->p.getText().trim())
                .collect(Collectors.toList());
    }
}
