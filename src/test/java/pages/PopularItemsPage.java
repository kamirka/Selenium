package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

public class PopularItemsPage extends BasePage {

    //zagwarantuje nam to, że webelementy nigdy nie będą nullami i będą zainicjalizowane
    public PopularItemsPage(WebDriver driver) {
    super(driver);
    }

    @FindBy(css = "#homefeatured .product-name")
    List<WebElement> productsNames;

    public List<String> getProductNames(){
       return productsNames.stream()
                .map (el ->el.getText().trim()) //trimm czyści białe spacje z przodu i z tyłu stringa
                .collect(Collectors.toList());
    }
}
