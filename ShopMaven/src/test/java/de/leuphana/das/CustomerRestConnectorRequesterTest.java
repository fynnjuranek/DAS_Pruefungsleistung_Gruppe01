package de.leuphana.das;

import de.leuphana.connector.CustomerRestConnectorRequester;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.BookCategory;
import de.leuphana.shop.structure.article.CD;
import de.leuphana.shop.structure.sales.Cart;
import de.leuphana.shop.structure.sales.CartItem;
import de.leuphana.shop.structure.sales.Customer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerRestConnectorRequesterTest {

    @Autowired
    CustomerRestConnectorRequester customerRestConnectorRequester;

    static Customer savedCustomer;
    static Book book, book2;
    static String orderId;

    @BeforeAll
    static void beforeAll() {
        book = new Book();
        book.setArticleId(1);
        book.setName("THINKING, FAST AND SLOW");
        book.setManufacturer("Penguin Verlag");
        book.setAuthor("Steven Pinker");
        book.setPrice(16.0f);
        book.setBookCategory(BookCategory.NON_FICTION);

        book2 = new Book();
        book2.setArticleId(2);
        book2.setName("Entwickeln von Web-Anwendungen");
        book2.setPrice(23.0f);
        book2.setBookCategory(BookCategory.POPULAR_SCIENCE);

        orderId = "TEST ORDER ID";

    }

    @Test
    @Order(1)
    void canCustomerBeAdded() {
        String customerName = "Test";
        String customerAddress = "Test address";
        Customer createdCustomer = new Customer(customerName, customerAddress);
        savedCustomer = customerRestConnectorRequester.addCustomer(createdCustomer);
        System.out.println("Added customer with customerId: " + savedCustomer.getCustomerId() + " and name: " + savedCustomer.getName());
        Assertions.assertNotNull(savedCustomer);
    }


    @Test
    @Order(2)
    void canOrderBeAddedToCustomer() {
        Customer customer = customerRestConnectorRequester.addOrderToCustomer(savedCustomer.getCustomerId(), orderId);
        System.out.println("Added orders to customer with id: " + savedCustomer.getCustomerId() + " and orderId: " + orderId);
        System.out.println("Order id's: ");
        for (String id : customer.getOrderIDs()) {
            System.out.println(id);
        }
        Assertions.assertNotNull(customer.getOrderIDs());
    }


    @Test
    @Order(3)
    void canCartBeUpdated() {
        CD cd = new CD();
        cd.setArticleId(1);
        cd.setArtist("Vampire Weekend");
        cd.setPrice(13.0f);
        cd.setName("Vampire Weekend");
        cd.setManufacturer("Test producer");
        Integer articleQuantity = 5;



        Cart cart = savedCustomer.getCart();
        cart.addCartItem(book, 3);
        cart.addCartItem(book2, 2);
        cart.addCartItem(cd, articleQuantity);
        Customer customer = customerRestConnectorRequester.updateCart(cart, savedCustomer.getCustomerId());
        System.out.println("New cart: ");
        for (CartItem cartItem : customer.getCart().getCartItems()) {
            System.out.println(cartItem.getArticleId());
            System.out.println(cartItem.getPrice());
        }
    }

    // TODO: Rename updateCart to addArticleToCart -> change method in CustomerService

//    @Test
//    @Order(4)
//    void canCustomerBeDeleted() {
//        boolean isCustomerDeleted = customerRestConnectorRequester.deleteCustomer(savedCustomer.getCustomerId());
//        Assertions.assertTrue(isCustomerDeleted);
//    }



}
