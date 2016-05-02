# YandexCalculator
Тестовое задание в отдел автоматизации тестирования

# Задача 
Разработать калькулятор в виде Rest-сервиса.

# Описание 

Модуль             | Назначение             | Технологии
------------------ | ------------------ | -------------
Calculator        | Реализация калькулятора арифметических выражений(+,-,*,/, унарный +,-, функции Cos,Sin,Abs | maven  
CalculatorServer   | Rest-сервис   | Jetty, Jersey, maven
CalculatorClient   | Киент с тестами |Restfit, maven

# Инструкция 
> mvn clean compile

запускаем сервер. 
>     cd CalculatorServer 
>     mvn exec:java
>     cd ../

После чего можно запускать тесты 
>     mvn test

 
