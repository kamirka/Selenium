package Tests;

import model.SearchQueryTop;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SearchQueryResultsPage;
import utils.PageBreadcrumbsUtil;
import utils.PageTitleUtils;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchQueryResultsTest extends BaseTest{
    private SearchQueryResultsPage searchQueryResultsPage;

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        System.out.println(driver.getTitle());
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        searchQueryResultsPage = new SearchQueryResultsPage(driver);
    }

    @Test
    @Order(1)
    public void shouldSeeSearchingResultList(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        SearchQueryTop search = new SearchQueryTop();
        search.setSearchQuery("Blouse");

        searchQueryResultsPage.searchingResultsList(search);

        assertThat(searchQueryResultsPage.getProductNameResult()).isEqualTo(search.getSearchQuery());
        assertThat(searchQueryResultsPage.getProductCountResult().trim()).isEqualTo("Showing 1 - 1 of 1 item");
        assertThat(searchQueryResultsPage.getProductPrice().trim()).isNotNull();
        assertThat(searchQueryResultsPage.checkSearchBreadcrumbs()).isEqualTo(PageBreadcrumbsUtil.SEARCH_BREADCRUMBS);
    }
}
