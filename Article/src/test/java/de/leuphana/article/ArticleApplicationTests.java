package de.leuphana.article;

import de.leuphana.article.structure.database.ArticleDatabase;
import de.leuphana.article.structure.database.entity.ArticleEntity;
import de.leuphana.article.structure.database.entity.BookEntity;
import de.leuphana.article.structure.database.entity.CdEntity;
import de.leuphana.article.structure.database.mapper.ArticleMapper;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleApplicationTests {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleDatabase articleDatabase;

    static CdEntity cdEntity;
    static BookEntity bookEntity;

    // TODO: Put mapping tests in separate test-class
    // TODO: Change name of test
    @Test
    @Order(1)
    void canBookEntityBeMapped() {
        bookEntity = new BookEntity(
                "Addison-Wesley", "The C++ Programming Language", 64.99f, "Bjarne Stroustrup"
        );
        Book book = articleMapper.mapToBook(bookEntity);
        System.out.println(book.getAuthor());
        Assertions.assertEquals("Bjarne Stroustrup", book.getAuthor());
    }

    @Test
    @Order(2)
    void canCdEntityBeMapped() {
        cdEntity = new CdEntity(
                "XL Recordings Ltd", "Vampire Weekend", 8.49f, "Vampire Weekend"
        );
        CD cd = articleMapper.mapToCd(cdEntity);
        System.out.println(cd.getArtist());
        Assertions.assertEquals("Vampire Weekend", cd.getArtist());
    }

    @Test
    @Order(3)
    void canBookEntityBeAdded() {
        Assertions.assertNotNull(articleDatabase.save(bookEntity));
    }

    @Test
    @Order(4)
    void canCdEntityBeAdded() {
        Assertions.assertNotNull(articleDatabase.save(cdEntity));
    }

    @Test
    @Order(5)
    void canBookEntityBeFound() {
        BookEntity bookEntity = null;
        ArticleEntity articleEntity = articleDatabase.findArticleEntityByName("The C++ Programming Language");
        if (articleEntity instanceof BookEntity) {
            bookEntity = (BookEntity) articleEntity;
            System.out.println(bookEntity.getAuthor());
        }
        Assertions.assertNotNull(bookEntity);
    }

    @Test
    @Order(6)
    void canCdEntityBeFound() {
        CdEntity cdEntity = null;
        ArticleEntity articleEntity = articleDatabase.findArticleEntityByName("Vampire Weekend");
        if (articleEntity instanceof CdEntity) {
            cdEntity = (CdEntity) articleEntity;
            System.out.println(cdEntity);
        }
        Assertions.assertNotNull(cdEntity);
    }

}
