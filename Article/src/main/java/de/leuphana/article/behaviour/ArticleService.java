package de.leuphana.article.behaviour;

import de.leuphana.article.structure.database.ArticleDatabase;
import de.leuphana.article.structure.database.entity.ArticleEntity;
import de.leuphana.article.structure.database.entity.BookEntity;
import de.leuphana.article.structure.database.entity.CdEntity;
import de.leuphana.article.structure.database.mapper.ArticleMapper;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// @Service is used for components that hold business logic -> data manipulation...
// https://stackoverflow.com/questions/58234187/what-is-the-use-of-service-layer-in-spring-boot-applications

@Service
public class ArticleService {

    @Autowired
    ArticleDatabase articleDatabase;

    @Autowired
    ArticleMapper articleMapper;

    public Article addArticleToDatabase(Article article) {
        ArticleEntity foundArticleEntity = articleDatabase.findArticleEntityByName(article.getName());
        if (foundArticleEntity != null) {
            articleDatabase.deleteById(foundArticleEntity.getArticleId());
            // TODO: Right now the articleId is adding up with every article (JPA-Generation), maybe add function to set the articleId after mapping (entity creation).
        }

        ArticleEntity articleEntity = null;
        if (article instanceof Book) {           // there would be information loss if no type-cast
            articleEntity = articleMapper.mapToBookEntity((Book) article);
        } else if (article instanceof CD) {
            articleEntity = articleMapper.mapToCdEntity((CD) article);
        }

        if (articleEntity != null) {
            articleDatabase.save(articleEntity);
        }

        return article;
    }

    public Article findArticleByName(String name) {
        ArticleEntity articleEntity = articleDatabase.findArticleEntityByName(name);
        Article article = null;
        if (articleEntity instanceof BookEntity) {
            article = articleMapper.mapToBook((BookEntity) articleEntity);
        } else if (articleEntity instanceof CdEntity) {
            article = articleMapper.mapToCd((CdEntity) articleEntity);
        }
        return article;
    }

    public List<Article> findAllArticles() {
        List<ArticleEntity> articleEntities = articleDatabase.findAll();
        List<Article> articles = new ArrayList<>();
        for (ArticleEntity articleEntity : articleEntities) {
            if (articleEntity instanceof BookEntity) {
                articles.add(articleMapper.mapToBook((BookEntity) articleEntity));
            } else if (articleEntity instanceof CdEntity) {
                articles.add(articleMapper.mapToCd((CdEntity) articleEntity));
            }
        }
        return articles;
    }

    public Article deleteArticleByName(String name) {
        ArticleEntity articleEntity = articleDatabase.findArticleEntityByName(name);
        articleDatabase.deleteById(articleEntity.getArticleId());
        return articleMapper.mapToArticle(articleEntity);
    }

}
