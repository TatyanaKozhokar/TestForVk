package parametrized.test;

import pages.WikiPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class WikiPageLinkBySearchedTextTest {

    public WikiPageLinkBySearchedTextTest(String searchedText, String clickedTest, String link) {
        this.searchedText = searchedText;
        this.clickedTest = clickedTest;
        this.link = link;
    }

    public String searchedText;
    public String clickedTest;
    public String link;

    @Parameterized.Parameters
    public static Object[][] filTheForm() {
        return new Object[][]{
                {"Долл", "Доллар США", "https://ru.wikipedia.org/wiki/%D0%94%D0%BE%D0%BB%D0%BB%D0%B0%D1%80_%D0%A1%D0%A8%D0%90"},
                {"Стак", "Стакан", "https://ru.wikipedia.org/wiki/%D0%A1%D1%82%D0%B0%D0%BA%D0%B0%D0%BD"},
        };
    }

    @Before
    public void setUp() {
        WikiPage wikiPage = new WikiPage();
        Selenide.open(wikiPage.getSetUpUrl());
    }

    @DisplayName("Проверка что при клике на текст происходит переход на его точную страницу")
    @Test
    public void testLinkBySearchedText(){
        WikiPage wikiPage = new WikiPage(); //создаем объект страницы
        wikiPage.clickOnSearchBarAndSetValue(searchedText); //Вводим текст
        wikiPage.getSearchClue(searchedText);//Проверяем что внизу списка есть подсказка Поиск страниц содержащих значение;

        wikiPage.getClickOnSearchedWord(clickedTest).click(); //нажимаем на один из предлагаемых вариантов
        wikiPage.checkTheLink(link); //Проверяем что открылась нужная ссылка

    }
}
