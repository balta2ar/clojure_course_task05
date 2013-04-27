# ClojureScript Pasta

Ищет изображения (перекладывает эту обязанность на jpg.to, на самом деле)
по указанным словам и добавляет их на страницу.

Введите одно или несколько слов, например "white cat", и нажмите Add. Будет
произведён поиск по этому слову среднего изображения и будет выдано случайное
из результата.

Для очистки страницы нажмите Reset.

Demo: http://pasta-balta2ar.rhcloud.com


## Предварительные требования

* Leiningent 2.0 или выше (http://leiningen.org)
* PhantomJS для тестирования (http://phantomjs.org)

## Запуск

Cкомпилируйте приложение.

    lein cljsbuild once

Запустите его: 

    lein ring server

Откройте приложение в браузере: http://localhost:3000/html/main.html

В БД настроены два пользователя: user/user и root/root.

## License

Copyright © 2013 Yuri Bochkarev
