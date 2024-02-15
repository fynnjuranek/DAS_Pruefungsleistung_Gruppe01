# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.2.2/reference/htmlsingle/index.html#using.devtools)
* [Eureka Server](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#spring-cloud-eureka-server)
* [Eureka Discovery Client](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#service-discovery-eureka-clients)
* [OpenFeign](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/)
* [Cloud Bootstrap](https://docs.spring.io/spring-cloud-commons/docs/current/reference/html/)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.2/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* 

### Guides

The following guides illustrate how to use some features concretely:
* [Beginner's Guide to Spring Cloud](https://www.youtube.com/watch?v=aO3W-lYnw-o)(youtube video)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb)
* [Building a Gateway](https://spring.io/guides/gs/gateway)
* [Messaging with JMS](https://spring.io/guides/gs/messaging-jms)
* [Connecting Spring JPA with MariaDB](https://www.javaguides.net/2020/01/spring-boot-mariadb-crud-example-tutorial.html)
 sonst noch ein YouTube video: (https://www.youtube.com/watch?v=Pf_nw4NWQLg).
* [Eureka and OpenFeign](https://www.baeldung.com/spring-cloud-netflix-eureka)
* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)

### Additional Links

These additional references should also help you:

* [Declarative REST calls with Spring Cloud OpenFeign sample](https://github.com/spring-cloud-samples/feign-eureka)

### Tipps
* Die Datenbank mit MariaDB muss seperat über den phpmyadmin angelegt werden. Die Daten/Tabellen werden dann automatisch 
  über JPA angelegt.
* Bei MariaDB darf man dann nicht vergessen die jeweiligen Server über "xampp" zu starten!
* Möglicherweise müssen Benutzername und Passwort angegeben werden. Dann kann man einfach für beide Sachen "root" benutzen
  (Das muss in "application.properties" angegeben werden)
* Bei MariaDB hat bei mir das Automatische generieren des Primary Keys nur hiermit funktioniert: @GeneratedValue(strategy = GenerationType.IDENTITY)
und nicht hiermit @GeneratedValue(strategy = GenerationType.AUTO)
* Für MongoDB muss folgendes installiert werden: https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-windows/
* Die Datenbanken können auch in die IDE eingebunden werden, wodurch die Verbindung zwischen Spring und
    der jeweiligen Datenbank erleichtert. (https://www.jetbrains.com/help/idea/connecting-to-a-database.html#data_source)
* Neue Module können dem Projekt folgend hinzugefügt werden:
File → Project Structure → Modules → + (oben links) und dann einfach ein neues Projekt/Modul erstellen. Dabei unbedingt
darauf achten, dass der Speicherort "DAS_Prüfungsleistung_Gruppe01" bleibt. Das Ganze ist dann so wie die Projekterstellung
in Eclipse. Beim Anlegen darauf achten den "Spring initialzr" zu benutzen, dadurch wird die Auswahl von Dependencies sehr leicht. 
Auch zum späteren Zeitpunkt kann man dann in der "pom.xml" auf "edit starters" klicken und darüber dann neue Spring-Dependencies hinzufügen.
* In manchen Tutorials werden ".yml"-Konfigurationsdateien benutzt, das ist veraltet und jetzt wird dafür "application.properties"
benutzt.
* Bei Eureka gibt es einen Fehler, durch den der Client nicht gestartet werden kann. [Das ist der fix](https://stackoverflow.com/questions/77684538/how-to-solve-netflix-eureka-client-error-in-spring-boot-3-2-0-and-java-17)
* OpenFeign wird benutzt um auf andere RestController (microservices) zu verweisen. In dem jeweiligen Service wird dann
der neue Request verarbeitet.
  * [Das hat mir ein bisschen geholfen](https://medium.com/@vegabryam40/simplifying-microservice-communication-exploring-openfeign-and-its-integration-with-spring-f53939a2c861)
  * [Die erste hälfte von dem Video war auch sehr hilfreich](https://www.youtube.com/watch?v=3NcmlrumSOc)
* 