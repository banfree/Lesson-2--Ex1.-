package Tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppCondition extends CoreTestCase
{
    @Test
    public void testChangeScreeOrientationOnSearchResults() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    String title_before_rotation = ArticlePageObject.getArticleTitle();
    this.rotateScreenLanscape();
    String title_after_rotation = ArticlePageObject.getArticleTitle();

    assertEquals(
            "Article title have been changed after screen rotation",
            title_before_rotation,
            title_after_rotation
    );

    this.rotateScreenPortrait();
    String title_after_second_rotation = ArticlePageObject.getArticleTitle();

    assertEquals(
            "Article title have been changed after screen rotation",
            title_before_rotation,
            title_after_second_rotation
    );
}

    @Test
    public void testCheckSearchArticleInBackground()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

}
