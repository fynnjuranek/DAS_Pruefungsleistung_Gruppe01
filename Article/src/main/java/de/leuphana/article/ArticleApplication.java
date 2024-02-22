package de.leuphana.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// If scanBasePackages is not added, then the ArticleRestConnectorProvider won't be found!
@SpringBootApplication(scanBasePackages = {"de.leuphana.connector", "de.leuphana.article"})
// TODO: Maybe need to add DiscoveryService
public class ArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }

}
