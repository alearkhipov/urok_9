package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class StartPages {
    public final SelenideElement searchField = $("[id=searchInput]");
    public final SelenideElement searchTags = $(".searching-results__inner");
    public final SelenideElement searchNEW = $(".searching-results__title");

    public StartPages openMainPage() {
        open("");
        return this;
    }
    public StartPages searchSomething(String searchQuery) {
        sleep(500);
        searchField.setValue(searchQuery).pressEnter();
        return this;
    }
    public StartPages searchSomethingNew(String gender, String searchQuery) {
        sleep(500);
        searchField.sendKeys(gender, searchQuery);
        searchField.pressEnter();
        sleep(1000);
        return this;
    }
    public StartPages crumbsProduct(String searchQuery) {
        searchTags.shouldHave(text(searchQuery));
        return this;
    }
    public StartPages crumbsGender(String gender) {
        searchNEW.shouldHave(text(gender));
        return this;
    }
}