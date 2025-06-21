import com.codeborne.selenide.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.WikiPage;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class WikiPageTest {

    @Before
    public void setUp() {
        WikiPage wikiPage = new WikiPage();
        open(wikiPage.getSetUpUrl());
    }
    @DisplayName("Проверка что все элементы выпадающего списка содержат введенное значение выделенное жирным")
    @Test
    public void testAllSuggestionsContainTextAndHaveBoldText() {
        WikiPage wikiPage = new WikiPage(); //создаем оъект страницы
        wikiPage.getSearchBar().click();    //нажимаем на поисковую строку
        wikiPage.getSearchBar().setValue("Долл"); //Вводим Долл
        wikiPage.getSuggestions()
                .shouldBe(Condition.visible); //Проверяем что выпадающий список виден
        wikiPage.getSearchClue("Долл") //Проверяем что внизу списка есть подсказка "Поиск страниц содержащих значение"
                .shouldBe(Condition.visible);
        wikiPage.getHighlight()
                .shouldBe(CollectionCondition.allMatch( //Проверяем что все совпадающие значения выделены жирным
                        "Все совпадения выделены жирным",
                        el -> el.getText().equals("Долл") &&
                                el.getCssValue("font-weight").equals("700")
                ));
    }



    @DisplayName("Проверка что движок предложит релевантные варианты при их наличии")
    @Test
    public void testRelevantOptions(){
        WikiPage wikiPage = new WikiPage(); //создаем объект страницы
        wikiPage.getSearchBar().click(); //нажимаем на поисковую строку
        wikiPage.getSearchBar().setValue("КисКи"); //Вводим значение, на которое движок может предложить похожее
        wikiPage.getSuggestions()
                .shouldBe(Condition.visible); //Смотрим что выпал список
        wikiPage.getSearchClue("КисКи") //Проверяем что внизу списка есть подсказка "Поиск страниц содержащих значение"
                .shouldBe(Condition.visible);

        assertThat("Тест невозможно автоматизировать, так как неизвестен алгоритм выдачи релевантных вариантов", 2, equalTo(42));
    }
}
