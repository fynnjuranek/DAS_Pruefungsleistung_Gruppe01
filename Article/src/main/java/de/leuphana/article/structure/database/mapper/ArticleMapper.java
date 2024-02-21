package de.leuphana.article.structure.database.mapper;

import de.leuphana.article.structure.database.entity.ArticleEntity;
import de.leuphana.shop.structure.article.Article;
import org.mapstruct.Mapper;

import java.util.List;

// componentModel = "spring" enables the dependency injection with @Autowired for the mapper!
@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleEntity mapToArticleEntity(Article article);

    List<ArticleEntity> mapToArticleEntities(List<Article> articles);
    Article mapToArticle(ArticleEntity articleEntity);
}
