package pages;

import model.SearchQueryTop;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchQueryResultsPage extends BasePage{

    public SearchQueryResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="search_query_top")
    WebElement searchQueryTop;

    @FindBy(name = "submit_search")
    WebElement submitSearch;

    @FindBy(className = "product-count")
    WebElement productCount;

    @FindBy(className = "navigation_page")
    WebElement breadcrumbs;

    @FindBy(xpath = "//*[@class='right-block']//*[@class='product-name']")
    WebElement productName;

    @FindBy(css = ".right-block > .content_price > span")
    WebElement productPrice;

    public void searchingResultsList(SearchQueryTop search){
        searchQueryTop.sendKeys(search.getSearchQuery());
        submitSearch.click();
    }

    public String checkSearchBreadcrumbs(){
        String breadcrumbsText = breadcrumbs.getText();
        return breadcrumbsText;
    }
    public String getProductNameResult(){
        String productNameText =productName.getText();
        return productNameText;
    }
    public String getProductCountResult(){
        String productCountText = productCount.getText();
        return productCountText;
    }
    public String getProductPrice(){
        String productPriceUSD = productPrice.getText();
        return productPriceUSD;
    }
}
