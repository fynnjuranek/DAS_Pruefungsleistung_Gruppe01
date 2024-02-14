# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.2.2/reference/htmlsingle/index.html#using.devtools)
* [OpenFeign](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/)
* [Cloud Bootstrap](https://docs.spring.io/spring-cloud-commons/docs/current/reference/html/)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.2/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb)
* [Building a Gateway](https://spring.io/guides/gs/gateway)
* [Messaging with JMS](https://spring.io/guides/gs/messaging-jms)


### Additional Links

These additional references should also help you:

* [Declarative REST calls with Spring Cloud OpenFeign sample](https://github.com/spring-cloud-samples/feign-eureka)

### Tipps
* Die Datenbank mit MariaDB muss seperat über den phpmyadmin angelegt werden. Die Daten/Tabellen werden dann automatisch 
  über JPA angelegt.
* Bei MariaDB darf man dann nicht vergessen die jeweiligen Server über "xampp" zu starten!
* Möglicherweise müssen Benutzername und Passwort angegeben werden. Dann kann man einfach für beide Sachen "root" benutzen
  (Das muss in "application.properties" angegeben werden)
* Für MongoDB muss folgendes installiert werden: https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-windows/
* Neue Module können dem Projekt folgend hinzugefügt werden:
File -> Project Structure -> Modules -> + (oben links) und dann einfach ein neues Projekt/Modul erstellen. Dabei unbedingt
drauf achten, dass der Speicherort "DAS_Prüfungsleistung_Gruppe01" bleibt. Das ganze ist dann so wie die Projekterstellung
in Eclipse.