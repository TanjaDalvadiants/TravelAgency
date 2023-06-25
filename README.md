# Курсовой проект по модулю «Автоматизация тестирования» для профессии «Инженер по тестированию»
Курсовой проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API банка, который предлагает купить тур по определённой цене двумя способами:

* Обычная оплата по дебетовой карте.
* Уникальная технология: выдача кредита по данным банковской карты.
## Документация 
1. [План автоматизации](https://github.com/TanjaDalvadiants/TravelAgency/blob/master/docs/Plan.md)
2. [Результаты тестирования](https://github.com/TanjaDalvadiants/TravelAgency/blob/master/docs/Report.md)
3. [Результат автоматизации](https://github.com/TanjaDalvadiants/TravelAgency/blob/master/docs/Summary.md)
## Этапы воспроизведения:
### MySQL
1. Открыть Intellij IDEA
2. Склонировать репозиторий  https://github.com/TanjaDalvadiants/TravelAgency
3. Открыть терминал и запустить контейнер: docker-compose -f docker-compose-msql.yml up -d
4. Открыть еще раз терминал и запустить SUT: java -jar artifacts/aqa-shop.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app 
5. Открыть приложение в браузере http://localhost:8080/
6. Запустить тесты: gradlew clean test
7. Посмотреть отчет Allure в браузере: ./gradlew allureServe или allureReport
8. Остановить контейнер: docker-compose -f docker-compose-msql.yml down
9. Отключить SUT: control+C
### PostgreSQL
1. Открыть Intellij IDEA
2. Склонировать репозиторий  https://github.com/TanjaDalvadiants/TravelAgency
3. Открыть терминал и запустить контейнер: docker-compose -f docker-compose-psql.yml up -d

4. Открыть еще раз терминал и запустить SUT: java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -Dlogin-app -Dpassword=pass -jar artifacts/aqa-shop.jar
5. Открыть приложение в браузере http://localhost:8080/
6. Запустить тесты: gradlew clean test
7. Посмотреть отчет Allure в браузере: ./gradlew allureServe или allureReport
8. Остановить контейнер: docker-compose -f docker-compose-psql.yml down
9. Отключить SUT: control+C