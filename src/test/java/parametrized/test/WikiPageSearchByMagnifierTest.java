package parametrized.test;

import pages.WikiPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class WikiPageSearchByMagnifierTest {
    WikiPage wikiPage;
    public String searchedText;
    public String link;

    public WikiPageSearchByMagnifierTest(String searchedText, String link) {
        this.link = link;
        this.searchedText = searchedText;
    }
    @Parameterized.Parameters
    public static Object[][] filTheForm() {
        return new Object[][]{
                {"Долл", "https://ru.wikipedia.org/wiki/%D0%94%D0%BE%D0%BB%D0%BB"},
                {"Иванннн", "https://ru.wikipedia.org/w/index.php?go=%D0%9F%D0%B5%D1%80%D0%B5%D0%B9%D1%82%D0%B8&search=%D0%98%D0%B2%D0%B0%D0%BD%D0%BD%D0%BD%D0%BD&title=%D0%A1%D0%BB%D1%83%D0%B6%D0%B5%D0%B1%D0%BD%D0%B0%D1%8F%3A%D0%9F%D0%BE%D0%B8%D1%81%D0%BA&ns0=1"},
        };
    }
    @Before
    public void setUp() {
        wikiPage = new WikiPage();
        Selenide.open(wikiPage.getSetUpUrl());
    }


    @DisplayName("Проверка что переход по нажатию лупы ведет к первому сайджесту")
    @Test
    public void testLinkWithMagnifier(){
        wikiPage.clickOnSearchBarAndSetValue(searchedText); //Проверяем что выпадающий список виден
        wikiPage.checkTheClue(searchedText);

        wikiPage.getMagnifier().click(); //Нажимаем на лупу
        wikiPage.checkTheLink(link); //Проверяем что открылась нужная ссылка

    }
}
