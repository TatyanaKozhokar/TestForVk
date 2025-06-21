package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
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

    @Name("Все результаты поиска")
    public ElementsCollection getAllSuggestions(){
        return $$(".suggestions-results .suggestions-result");
    }

    @Name("Список значений в начале которых совпадают введенные символы")
    public ElementsCollection getHighlight(){
        return $$(".suggestions-results span.highlight");
    }
    @Name("Поиск полного совпадения")
    public SelenideElement getCompleteMatch(String searchText) {
       return  $x("//span[@class='highlight' and text()='" + searchText + "']");
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

}
