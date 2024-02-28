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

@Service
public class ArticleService {

    @Autowired
    ArticleDatabase articleDatabase;

    @Autowired
    ArticleMapper articleMapper;

    public Article addArticleToDatabase(Article article) {
        if (article instanceof Book) {
            BookEntity bookEntity = articleMapper.mapToBookEntity((Book) article);
            articleDatabase.save(bookEntity);
        } else if (article instanceof CD) {
            CdEntity cdEntity = articleMapper.mapToCdEntity((CD) article);
            articleDatabase.save(cdEntity);
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

    // TODO: add more functionality

}
