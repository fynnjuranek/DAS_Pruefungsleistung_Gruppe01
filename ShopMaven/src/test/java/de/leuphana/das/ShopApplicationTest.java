package de.leuphana.das;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;
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
    CD cd;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setName("Sprechen Sie Java?");
        book.setManufacturer("d.punkt.verlag");
        book.setAuthor("Hanspeter Mössenböck");
        book.setPrice(29.90f);
        // TODO: add bookcategory

        cd = new CD();
        cd.setName("Vampire Weekend");
        cd.setManufacturer("XL Recordings Ltd");
        cd.setArtist("Vampire Weekend");
        cd.setPrice(8.49f);
    }

    // Test works if Gateway and Article are started already
    // maybe it needs 1-2 seconds so article is 100% registered on the discovery-service (eureka-server)
    @Test
    void canBookBeAdded() {
        Assertions.assertNotNull(shopService.addBook(book));
        // TODO: Maybe better solution to implement a mocker for article and eureka... dont know
    }

    @Test
    void canCDBeAdded() {
        Assertions.assertNotNull(shopService.addCD(cd));
    }
}
