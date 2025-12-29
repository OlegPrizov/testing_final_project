# Итоговый проект по дисциплине «Тестирование сервисов и приложений»

В проекте представлены тесты для сайта Wikipedia и Android‑приложения Wikipedia.

## Технические требования
- JDK версии 11+
- Maven версии 3.8+
- Браузер (лучше Chrome)
- Для мобильных тестов: Android SDK + эмулятор/устройство, запущенный Appium Server, установленное приложение Wikipedia (`org.wikipedia`)

## Структура проекта
Основной код проекта лежит в src/java/com/uitesting, где есть два пакета – pages и tests
- `src/test/java/com/uitesting/pages/web` — Page объекты для веб-версии.
- `src/test/java/com/uitesting/tests/web` — веб-тесты.
- `src/test/java/com/uitesting/pages/mobile` — Page объекты для мобильного приложения.
- `src/test/java/com/uitesting/tests/mobile` — мобильные тесты.
- `src/test/resources/testng.xml` — конфигурация TestNG

## Как запустить веб-тесты?
- `mvn test -Dgroups=web`

## Как запустить мобильные тесты?
Аминь

## Примечания
- Драйверы браузеров ставятся автоматически через WebDriverManager; Chrome стартует в maximized.
- Мобильные тесты используют UiAutomator2 и `noReset=true`; онбординг пропускается, если доступна кнопка Skip.
- Все ключевые параметры можно переопределять через системные свойства без правок кода.