
## Запуск для Yandex

- В `-Ddriver.version={version}` необходимо указать версию драйвера для своего браузера. Версии можно посмотреть в файле `driversVersions.json`
- В `-Dwebdriver.yandex.bin={path}` необходимо указать путь до браузера

```bash
mvn clean test -Dbrowser=yandex -Ddriver.version=128.0.6613.137 -Dwebdriver.yandex.bin=C:\Users\User\AppData\Local\Yandex\YandexBrowser\Application\browser.exe
```

Если надо просто запустить

```bash
mvn clean test 
```