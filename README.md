# Дипломный проект: "sportWebShop2_RestSecurity"
* Выполнил: Сеппар Н.А. группа "Java112"

* Тема: "Web-магазин спортивной одежды"

*Исполняемый файл проекта: sportWebShop2_RestSecurity.jar

*Dump базы данных: sportWebShop2_Rest.sql

* Системные требования к запуску:
JDK-17.0.5 или выше

СУБД PostgreSQL:

url: 'jdbc:postgresql://localhost:5432/sportShop2'

username: postgres

password: rootroot

* Порядок запуска проекта:

В СУБД PostgreSQL заполнить нач.данными базу данных "sportShop2",
для чего выполнить запуск скрипта командой:

pg_dump -U postgres sportWebShop2_Rest < sportWebShop2_Rest.sql

Запустить исполняемый файл в терминале командой: java -jar sportWebShop2_RestSecurity.jar
