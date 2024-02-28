package de.leuphana.article;

import de.leuphana.article.behaviour.ArticleService;
import de.leuphana.article.structure.database.ArticleDatabase;
import de.leuphana.article.structure.database.entity.BookEntity;
import de.leuphana.article.structure.database.entity.CdEntity;
import de.leuphana.article.structure.database.mapper.ArticleMapper;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleServiceTests {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleDatabase articleDatabase;

    @Autowired
    ArticleService articleService;


    static CD cd;
    static Book book;

    // TODO: Put mapping tests in separate test-class
    // TODO: Change name of test
    @Test
    @Order(1)
    void canBookEntityBeMapped() {
        BookEntity bookEntity = new BookEntity(
                "Addison-Wesley", "The C++ Programming Language", 64.99f, "Bjarne Stroustrup"
        );
        book = articleMapper.mapToBook(bookEntity);
        System.out.println("Mapped bookEntity to book: " + book.getAuthor());
        Assertions.assertEquals("Bjarne Stroustrup", book.getAuthor());
    }

    @Test
    @Order(2)
    void canCdEntityBeMapped() {
        CdEntity cdEntity = new CdEntity(
                "XL Recordings Ltd", "Vampire Weekend", 8.49f, "Vampire Weekend"
        );
        cd = articleMapper.mapToCd(cdEntity);
        System.out.println("Mapped cdEntity to cd with: " + cd.getArtist());
        Assertions.assertEquals("Vampire Weekend", cd.getArtist());
    }

    @Test
    @Order(3)
    void canBookEntityBeAdded() {
        Article addedArticle = articleService.addArticleToDatabase(book);
        System.out.println("Successfully added: " + addedArticle.getName());
        Assertions.assertNotNull(addedArticle);
    }

    @Test
    @Order(4)
    void canCdEntityBeAdded() {
        Article addedArticle = articleService.addArticleToDatabase(cd);
        System.out.println("Successfully added: " + addedArticle.getName());
        Assertions.assertNotNull(addedArticle);
    }

    @Test
    @Order(5)
    void canBookBeFound() {
        Article article = articleService.findArticleByName("The C++ Programming Language");
        Book foundBook = null;
        if (article instanceof Book) {
            foundBook = (Book) article;
            System.out.println("Successfully found book: " + foundBook.getAuthor());
        }
        Assertions.assertNotNull(foundBook);
    }

    @Test
    @Order(6)
    void canCdBeFound() {
        Article article = articleService.findArticleByName("Vampire Weekend");
        CD foundCD = null;
        if (article instanceof CD) {
            foundCD = (CD) article;
            System.out.println("Successfully found cd: " + foundCD.getArtist());
        }
        Assertions.assertNotNull(foundCD);
    }

    @Test
    @Order(7)
    void canArticleBeDeleted() {
        Article article = articleService.deleteArticleByName("Vampire Weekend");
        System.out.println("Successfully deleted article: " + article.getName());
        Assertions.assertNotNull(article);
    }

}
