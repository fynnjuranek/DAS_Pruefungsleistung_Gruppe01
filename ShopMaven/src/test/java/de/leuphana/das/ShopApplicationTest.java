package de.leuphana.das;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.article.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopApplicationTest {

    @Autowired
    ShopService shopService;

    Book book;
    @BeforeEach
    void setUp() {
        book = new Book();
        book.setName("Sprechen Sie Java?");
        book.setManufacturer("d.punkt.verlag");
        book.setAuthor("Hanspeter Mössenböck");
        book.setPrice(29.90f);
        // TODO: add bookcategory
    }

    @Test
    void canBookBeAdded() {
        Assertions.assertNotNull(shopService.addBook(book));
        // TODO: Need to implement something like a mocker for the other services (Eureka, ArticleApplication)
    }
}
