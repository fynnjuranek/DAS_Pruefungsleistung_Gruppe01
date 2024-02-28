package de.leuphana.article.structure.database.mapper;

import de.leuphana.article.structure.database.entity.ArticleEntity;
import de.leuphana.article.structure.database.entity.BookEntity;
import de.leuphana.article.structure.database.entity.CdEntity;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;
import org.mapstruct.Mapper;

import java.util.List;

// componentModel = "spring" enables the dependency injection with @Autowired for the mapper!
@Mapper(componentModel = "spring")
public interface ArticleMapper {
    BookEntity mapToBookEntity(Book book);
    CdEntity mapToCdEntity(CD cd);

    List<BookEntity> mapToBookEntities(List<Book> books);
    Book mapToBook(BookEntity bookEntity);
    CD mapToCd(CdEntity cdEntity);

    Article mapToArticle(ArticleEntity articleEntity);
}
