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
        open("https://www.gazprom.ru/");
        $$(".additional-links b, .additional-links a").find(text(language.name())).click();
        $("#sites_link").shouldHave(text(language.description));
    }

    static Stream<Arguments>afanasySiteShouldCorrectButtons(){
        return Stream.of(
                Arguments.of
                        (Language.RU,
                                List.of("О «Газпроме»", "Акционерам и инвесторам", "Пресс-центр", "Проекты", "Устойчивое развитие", "Противодействие мошенничеству", "Контактная информация", "Реализация активов", "Карьера", "Закупки")
                        ),
                Arguments.of
                        (Language.EN,
                                List.of("About Gazprom", "Investors", "Media", "Projects", "Sustainable development", "Countering fraud", "Contact us")
                        ),
                Arguments.of
                        (Language.DE,
                         List.of("Über Gazprom", "Aktionäre und Investoren", "Pressezentrum", "Projekte", "Nachhaltige Entwicklung", "Betrugsbekämpfung", "Kontakt")
                )
        );
    }

    @MethodSource
    @ParameterizedTest (name = "Для поискового запроса {0} должен быть текст {1}")
    void afanasySiteShouldCorrectButtons(Language language, List<String> expectedButtons) {
        open("https://www.gazprom.ru/");
        $$(".additional-links b, .additional-links a").find(text(language.name())).click();
        $$("[class='no_visited']").filter(visible).shouldHave(texts(expectedButtons));
    }

}