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
- установить Android SDK
- Добавить переменные окружения
`export ANDROID_HOME="$HOME/Library/Android/sdk"`
`export ANDROID_SDK_ROOT="$ANDROID_HOME"`
`export PATH="$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/emulator"`
- Установить Appium и драйвер UiAutomator2
- Запустить Appium Server
- Запустить эмулятор или подключите устройство.
- Запустить только мобильные тесты
`mvn test -Dgroups=mobile \
  -DappiumServerUrl=http://127.0.0.1:4723/wd/hub \
  -DdeviceName="emulator-5554" \
  -DplatformVersion=16`