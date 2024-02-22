package de.leuphana.article.behaviour;

import de.leuphana.article.structure.database.ArticleDatabase;
import de.leuphana.article.structure.database.entity.BookEntity;
import de.leuphana.article.structure.database.mapper.ArticleMapper;
import de.leuphana.shop.structure.article.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    ArticleDatabase articleDatabase;

    @Autowired
    ArticleMapper articleMapper;

    public Book addBookToDatabase(Book book) {
        BookEntity bookEntity = articleMapper.mapToBookEntity(book);
        // returns the book to see that it worked!
        return articleMapper.mapToBook(articleDatabase.save(bookEntity));
    }

    public Book findBookByName(String name) {
        BookEntity bookEntity = (BookEntity) articleDatabase.findArticleEntityByName(name);
        Book book = articleMapper.mapToBook(bookEntity);
        return book;
    }

    // TODO: add more functionality

}
