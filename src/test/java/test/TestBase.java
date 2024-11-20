package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pages.StartPages;
public class TestBase {
     StartPages startPages = new StartPages();

    @BeforeAll
    static void precondition(){
        Configuration.baseUrl = "https://www.wildberries.ru/";
        Configuration.browserPosition = "0x0";
        Configuration.browserSize = "2560x1600";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }
}