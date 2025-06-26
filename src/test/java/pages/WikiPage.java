package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import jdk.jfr.Name;

import static com.codeborne.selenide.Selenide.*;

public class WikiPage {

    public String getSetUpUrl() {
        return "https://ru.wikipedia.org/wiki/Заглавная_страница";
    }


    @Name("Строка поиска")
    public SelenideElement getSearchBar() {
        return $("input[class='vector-search-box-input']");
    }

    @Name("Выпадающий список")
    public SelenideElement getSuggestions() {
        return $("div[class='suggestions-results']");
    }

    @Name("Список значений в начале которых совпадают введенные символы")
    public ElementsCollection getHighlight(){
        return $$(".suggestions-results span.highlight");
    }

    @Name("")
    public SelenideElement getClickOnSearchedWord(String data) {
        return  $("a[title='" + data +"']");
    }

    @Name("Локатор лупы")
    public SelenideElement getMagnifier(){
        return $("input[id='searchButton']");
    }

    @Name("Локатор подсказки Поиск страниц содержащих значение")
    public SelenideElement getSearchClue(String searchText){
        return $x("//div[@class='special-query' and text()='" + searchText + "']");
    }

    @Step("Ввод значения в поисковую строку")
    public void clickOnSearchBarAndSetValue(String data){
        getSearchBar().click(); //Нажимаем на поисковую строку
        getSearchBar().setValue(data); //Вводим значение
        getSuggestions()
                .shouldBe(Condition.visible); //Проверяем наличие выпадающего списка
    }

    @Step("Проверка наличия подсказки внизу выпадающего списка")
    public void checkTheClue(String data){
        getSearchClue(data) //Проверяем что внизу списка есть подсказка "Поиск страниц содержащих значение"
                .shouldBe(Condition.visible);
    }

    @Step("Проверка ссылки")
    public void checkTheLink(String data){
        Selenide.webdriver().shouldHave(WebDriverConditions.url(data));
    }

}
