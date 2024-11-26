package test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
                .searchTitle(expectedText);

    }

    @CsvFileSource(resources = "/test_data/searchResultsTitleNotBeEmpty.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должен быть текст {1}")
    void searchResultsTitleNotBeEmptyCsv(String searchQuery, String expectedText) {
        startPages
                .openMainPage()
                .searchSomething(searchQuery)
                .searchResultText(expectedText);
    }
}