# REST сервис-адаптер к SOAP веб-серису Calculator
<h2>Описание</h2>
<p>REST сервис-адаптер к SOAP веб-серису Calculator (http://www.dneonline.com/calculator.asmx).
<p>Работа с SOAP сервисом осуществляется с помощью Apache CXF. Java код сервиса генерируется из документа WSDL плагином wsdl2java.
<p>Предусмотрена валидация запросов к REST-сервису на предмет их наличия, корректности формата и наличия значения.
<p>Сервис обладает спецификацией в формате OpenAPI (SWAGGER) (localhost:8080/swagger-ui.html)
<p>Запросы кешируются в распределенном кеше (Hazelcast).
<h2>Запуск тестов</h2>
mvn clean test 
<h2>Запуск приложения</h2>
<p>mvn spring-boot:run
<h2>Запуск приложения (Docker)</h2>
<p>mvn package
<h3>Запуск одного экземпляра приложения</h3>
<p>docker build -t calculator-docker .
<p>docker run -p 8080:8080 calculator-docker
<h3>Запуск кластера для проверки распределенного кеша</h3>
<p>Запускается 2 экземпляра приложения на портах :8080 и :8081.
<p>docker-compose up --build
