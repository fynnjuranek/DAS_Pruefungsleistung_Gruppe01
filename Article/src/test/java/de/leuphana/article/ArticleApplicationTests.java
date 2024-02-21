package de.leuphana.article;

import de.leuphana.article.structure.database.ArticleDatabase;
import de.leuphana.article.structure.database.entity.ArticleEntity;
import de.leuphana.article.structure.database.mapper.ArticleMapper;
import de.leuphana.shop.structure.article.Article;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleApplicationTests {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleDatabase articleDatabase;

    @Test
    void contextLoads() {
    }

    @Test
    void canArticleEntityBeMapped() {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setManufacturer("Fynn");
        Article article = articleMapper.mapToArticle(articleEntity);
        System.out.println(article.getManufacturer());
        Assertions.assertEquals("Fynn", article.getManufacturer());
    }

    @Test
    void canArticleBeAdded() {
        Assertions.assertNotNull(articleDatabase.save(new ArticleEntity(
                "Kodak", "Polaroid Kamera", 60.0f
        )));
    }


}
