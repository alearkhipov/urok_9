package test;

import data.Language;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SearchResultsShouldNotBeEmptyTest extends TestBase {

    @ValueSource(strings = {
            "квас", "пиво", "снеки"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} результат поиска должен содержать слово {0}")
    void searchResultsTitleNotBeEmpty(String searchQuery) {
        startPages
                .openMainPage()
                .searchSomething(searchQuery)
                .searchPage(searchQuery);
    }

    @CsvSource(value = {
            "Пиво, Ординарное: простое пиво с русским характером",
            "Квас, Квас «Бочковой»",
            "Снеки, Ломтики мясные и рыбные – любимые снеки в новой упаковке"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должен быть заголовок {1}")
    void searchResultsTitleNotBeEmpty(String searchQuery, String expectedText) {
        startPages
                .openMainPage()
                .searchSomething(searchQuery)
                .searchResultTitle(expectedText);

    }

    @CsvFileSource(resources = "/test_data/searchResultsTitleNotBeEmpty.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должен быть текст {1}")
    void searchResultsTitleNotBeEmptyCsv(String searchQuery, String expectedText) {
        startPages
                .openMainPage()
                .searchSomething(searchQuery)
                .searchResultText(expectedText);
    }

    @EnumSource(Language.class)
    @ParameterizedTest
    void afanasySiteShouldCorrectText(Language language){
        open("https://www.afanasy.ru/");
        $$(".top-head__switch a").find(text(language.name())).click();
        $("h2.main-video__title").shouldHave(text(language.description));
    }

    static Stream<Arguments>afanasySiteShouldCorrectButtons(){
        return Stream.of(
                Arguments.of(
                        Language.Рус,
                        List.of("Компания", "Продукция", "Партнерам", "Работа у нас", "Новости", "Холдинг")
                ),
                Arguments.of(Language.Eng,
                        List.of("COMPANY", "PRODUCTS", "PARTNERS", "WORK WITH US", "NEWS", "HOLDING")
                )
        );
    }

    @MethodSource
    @ParameterizedTest
    void afanasySiteShouldCorrectButtons(Language language, List<String> expectedButtons) {
        open("https://www.afanasy.ru/");
        $$(".top-head__switch a").find(text(language.name())).click();
        $$("#bs-example-navbar-collapse-1 li").filter(visible).shouldHave(texts(expectedButtons));
    }

}