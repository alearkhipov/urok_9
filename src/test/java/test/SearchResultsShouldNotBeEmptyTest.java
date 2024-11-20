package test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SearchResultsShouldNotBeEmptyTest extends TestBase {

    @ValueSource(strings = {
            "джинсы", "кепки", "куртки"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} заголовок результата поиска должен содержать слово {0}")
    //@Tag("BLOCKER")
    void searchResultsTitleNotBeEmpty(String searchQuery) {
        startPages
                .openMainPage()
                .searchSomething(searchQuery)
                .crumbsProduct(searchQuery);
    }

    @CsvSource(value = {
            "мужская| футболка",
            "женская| футболка"
    },delimiter = '|')
    @ParameterizedTest(name = "Для поискового запроса заголовок результата поиска должен содержать слово {0}")
    void searchResultsGenderNotBeEmpty(String gender, String searchQuery) {
        startPages
                .openMainPage()
                .searchSomethingNew(gender, searchQuery)
                    .crumbsGender(gender);
    }

    @CsvFileSource(resources = "/test_data/searchResultsGenderNotBeEmptyCsv.csv")
    @ParameterizedTest(name = "Для поискового запроса заголовок результата поиска должен содержать слово {0}")
    void searchResultsGenderNotBeEmptyCsv(String gender, String searchQuery) {
        startPages
                .openMainPage()
                .searchSomethingNew(gender, searchQuery)
                .crumbsGender(gender);
    }
}