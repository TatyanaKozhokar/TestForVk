# README for TestForVk Project

Проект автоматизированного тестирования поисковой строки сервиса https://ru.wikipedia.org

Проект содержит в себе тесты:

✅ Проверка что все элементы выпадающего списка содержат введенное значение и оно выделено жирным

            - mvn test -Dtest=WikiPageTest#testAllSuggestionsContainTextAndHaveBoldText

✅ Проверка что при клике на текст происходит переход на его точную страницу. Проверен с помощью параметризации

            - mvn test -Dtest=WikiPageLinkBySearchedTextTest#testLinkBySearchedText

✅ Проверка что переход по нажатию лупы ведет к первому сайджесту. С помощью параметризации проверено как значение, имеющееся в выпадающем списке поисковой строки, так и отсутствующее совпадение в поисковой строке

            - mvn test -Dtest=WikiPageSearchByMagnifierTest#testLinkWithMagnifier

❌ Не удалось автоматизировать подбор релевантных значений при отсутствии совпадения в выпадающем списке, так как неизвестен алгоритм подбора релевантных значений

            - mvn test -Dtest=WikiPageTest#testRelevantOptions


❕ Запуск всех тестов

            - mvn clean test

❕ Генерация и запуск отчета Allure

            - mvn allure:serve