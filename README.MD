# Smart adder service
Service that allows you to add books to DB just writing partial title and author.

## Java - 8

## Used libs info

1) spring boot
2) postgresql (Driver)
3) spring-boot-starter-data-jpa
4) bucket4j-core
5) spring-boot-starter-web
6) spring-boot-starter-jdbc
7) lombok
8) spring-boot-starter-tomcat
9) spring-boot-starter-test

## Basic theses about the project.
1) Have layered architecture.
2) DAO layer - pure JDBC with prepared statements
3) Repository layer (optional feature) - Spring Data JPA
4) Configuration created mostly with Annotations but for example all beans of DAO layer are in XML file.
5) RestFacade its layer where we contact with different apis.
6) SmartAdderService - layer with implemented smart adder service.
7) Implemented GoogleBooks api support.
8) Implemented mockapi api support.
9) Created DOCKERFILE for both services.
10) Created docker-compose.yml for initializing whole container with 3 services(postgres,lab2,lab3)
11) Created .bat scripts that allows you to build/run/remove compose instance/remove compose images etc.

## Summary

Реализовал для начала DAO слой, потом доделал Spring Data JPA слой,
что бы можно было и так и так иметь возможность брать/запихивать данные с/в БД.
Так же реализовал 2  разных имплементации SmartAdderService для работы с
Google Api и Mockapi. Так же по личной инициативе переписал конфигурацию бинов DAO
слоя на XML(Хотел всю приложуху так переписать, но куратор сказал что и пары бинов хватит).
Запросы к апишкам делал через RestTemplate, но он в свою очередь для десериализации исползует
Jackson, аннотации работают соответственно джексоновские. Все запросы(1) подбил под унифицированный
ResponseObject<Type> что бы облегчить обработку ответов. Так же добавил ControllerAdvice который ловит все
Exception'и. Так же добавил в каждый из сервисов Dockerfile, докерфайл короткий, основные настройки перенес
в докер-компоуз. Так же сделал всякие батники для того что бы удобно было перезапускать докер компоуз,
можно как перезапустить с наявными имейджами, а можно сделать хард ресет ран, при котором он удаляет всю наявную 
информацию о старом компоузе.