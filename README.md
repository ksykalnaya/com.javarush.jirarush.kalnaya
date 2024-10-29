## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

Список выполненных задач:
1) Удалены социальные сети: vk, yandex. Заблокировано на экране. Классы помечены аннотацией @Deprecated
2) Вынесена информацию в отдельный проперти файл для профиля конфигурации dev. Создан application-dev.yaml
3) Тесты используют БД (H2). Настройка прописала в application-test.properties. Изменены файлы changelog.sql + data.sql
4) Написаны тесты для публичных методов контроллера ProfileRestController с положительными и отрицательными сценариями
5) Произведен рефакторинг метода FileUtil.upload c IO -> NIO
6) Сделана локализация для стартовой страницы index.html и писем. Property с локалями сохранены в src/main/resources/message
7) 
6) Добавить новый функционал: добавления тегов к задаче (REST API + реализация на сервисе). Фронт делать необязательно

