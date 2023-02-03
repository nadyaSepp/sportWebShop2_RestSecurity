# Дипломный проект: sportWebShop2_RestSecurity
* Исполнитель: Сеппар Н.А. группа "Java112"

*Тема: "Web-магазин спортивной одежды"

*Исполняемый файл проекта: sportWebShop2_RestSecurity.jar

*Скрипт создания базы данных: create.sql

*Требования к запуску:

OC Windows 10.0 или выше
Наличие СУБД PostgreSQL с настройкой параметров:

url: 'jdbc:postgresql://localhost:5432/sportShop2'

username: postgres

password: rootroot

*Порядок запуска проекта:

В СУБД PostgreSQL создать базу данных "sportShop2" для чего выполнить запуск скрипта create.sql командой:
pg_dump -U postgres sportWebShop2_Rest < create.sql
Запустить исполняемый файл в терминале командой: java -jar sportWebShop2_RestSecurity.jar
