package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartPages {
    public final SelenideElement searchField = $(".search-c");
    public final SelenideElement searchInput = $(".col-md-8 input");
    public final SelenideElement searchTitle = $(".search__item");
    public final SelenideElement searchResultText = $(".search-descr");


    public StartPages openMainPage() {
        open("");
        return this;
    }
    public StartPages searchSomething(String searchQuery) {
        searchField.setValue(searchQuery).pressEnter();
        return this;
    }
    public StartPages searchPage(String searchQuery) {
        assertEquals (searchInput.getAttribute("value"),searchQuery);
        return this;
    }
    public StartPages searchTitle(String expectedText) {
        searchTitle.shouldHave(text(expectedText));
        return this;
    }
    public StartPages searchResultText(String expectedText) {
        searchTitle.shouldHave(text(expectedText));
        return this;
    }
}