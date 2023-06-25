## Отчет по итогам тестирования
### Краткое описание
Проведено тестирование комплексного сервиса, взаимодействующего с СУБД и API Банка. Были задействованы все инструменты, заявленные в [Плане автоматизации тестирования](https://github.com/TanjaDalvadiants/TravelAgency/blob/master/docs/%5BPlan.md%5D(Plan.md)).

### Итоги
1. Подключен SUT
2. Подключены СУБД MySQL и PostgreSQL
3. Реализованы позитивные и негативные сценарии покупки тура:
* Общее количество тест-кейсов 22 из
* Количество успешных 14 - это 64% от общего количества
* Количество упавших 8

![Monosnap Allure Report 2023-06-24 17-06-31.jpg ](..%2Fpic%2FMonosnap%20Allure%20Report%202023-06-24%2017-06-31.jpg )


##### Отчет в Allure  можно прочитать по ссылке [Allure](http://localhost:63342/Travel/allure-report/allureReport/index.html?_ijt=cgli358aggh9rr6fg34q7t6het&_ij_reload=RELOAD_ON_SAVE#)

* Обнаружено Х багов и оформлены [баг-репорты к ним](https://github.com/TanjaDalvadiants/TravelAgency/issues)
![Monosnap Issues · TanjaDalvadiants:TravelAgency 2023-06-24 17-37-07.jpg](..%2Fpic%2FMonosnap%20Issues%20%C2%B7%20TanjaDalvadiants%3ATravelAgency%202023-06-24%2017-37-07.jpg)


### Рекомендации
1. Закрыть баг-репорты в [Issue](https://github.com/TanjaDalvadiants/TravelAgency/issues)
* Исправить ошибку в названии тура
* Изменить сообшение Успешо на сообщение Ошибка при попытке ввода номера карты для отклоненной операции
* Отменить возможность ввода имени кириллицей в поле Владелец 
* Отменить возможность ввода имени цифр и символов в поле Владелец
* Имя в поле Владелец не должно быть менее 2х букв
* Изменить количество возможного ввода букв имени на менее, чем 65
* Стоит проработать конкретные название всплывающих подсказок для пользователя когда поля не заполнены
* 
2. Изучить логи [упавших тестов](http://localhost:63342/Travel/allure-report/allureReport/index.html?_ijt=cgli358aggh9rr6fg34q7t6het&_ij_reload=RELOAD_ON_SAVE#suites/16b98e3b12bb1c59d4896f22aff84fdb)