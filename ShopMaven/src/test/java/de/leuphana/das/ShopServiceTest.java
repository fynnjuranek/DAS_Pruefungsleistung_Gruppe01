package de.leuphana.das;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.BookCategory;
import de.leuphana.shop.structure.article.CD;
import de.leuphana.shop.structure.sales.Customer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShopServiceTest {

    @Autowired
    ShopService shopService;

    static Article addedBook;
    static Article addedCD;
    static Integer customerId;

    @Test
    @Order(1)
    void canCustomerBeCreated() {
        String customerName = "Max Mustermann";
        String customerAddress = "Musterstraße 32, 21335 Lüneburg";
        customerId = shopService.createCustomer(customerName, customerAddress);
        System.out.println("Customer with id: " + customerId + " has been created.");
        Assertions.assertNotNull(customerId);
    }

    @Test
    @Order(2)
    void canArticlesBeAddedToDatabase() {
        Book book = new Book();
        book.setName("THINKING, FAST AND SLOW");
        book.setManufacturer("Penguin Verlag");
        book.setAuthor("Steven Pinker");
        book.setPrice(16.0f);
        book.setBookCategory(BookCategory.POPULAR_SCIENCE);
        CD cd = new CD();
        cd.setName("GO:OD AM");
        cd.setArtist("Mac Miller");
        cd.setPrice(16.99f);
        cd.setManufacturer("Warner Records Inc.");
        addedBook = shopService.addNewArticleToCatalog(book);
        addedCD = shopService.addNewArticleToCatalog(cd);
        Assertions.assertEquals(book.getName(), addedBook.getName());
    }

    @Test
    @Order(3)
    void canArticlesBeAddedToCart() {
        Integer bookQuantity = 4;
        Integer cdQuantity = 2;
        shopService.addArticleToCart(customerId, addedBook.getArticleId(), bookQuantity);
        Customer customer = shopService.addArticleToCart(customerId, addedCD.getArticleId(), cdQuantity);

        Integer numberOfAddedDistinctArticles = 2;
        Assertions.assertEquals(numberOfAddedDistinctArticles, customer.getCart().getNumberOfArticles());

    }

    @Test
    @Order(4)
    void canCheckOutCartBeCreated() {
        de.leuphana.shop.structure.sales.Order order = shopService.checkOutCart(customerId);
        // This is explicitly for the newest order we added to the customer.
        // Otherwise, we would get an error when running this test multiple times
        // because one customer can hold multiple orderIds
        List<String> orderIDsOfCustomer = shopService.getCustomer(customerId).getOrderIDs();
        String addedOrderIdOfCustomer = orderIDsOfCustomer.get(orderIDsOfCustomer.indexOf(order.getOrderId()));

        Assertions.assertEquals(order.getOrderId(), addedOrderIdOfCustomer);
    }


}
