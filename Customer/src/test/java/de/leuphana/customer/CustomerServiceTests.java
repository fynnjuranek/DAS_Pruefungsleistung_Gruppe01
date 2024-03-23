package de.leuphana.customer;

import de.leuphana.customer.behaviour.CustomerService;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.BookCategory;
import de.leuphana.shop.structure.article.CD;
import de.leuphana.shop.structure.sales.Order;
import de.leuphana.shop.structure.sales.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTests {

    @Autowired
    CustomerService customerService;

    static Customer customer;

    @BeforeAll
    static void beforeAll() {
        Book book = new Book();
        book.setArticleId(1);
        book.setName("THINKING, FAST AND SLOW");
        book.setManufacturer("Penguin Verlag");
        book.setAuthor("Steven Pinker");
        book.setPrice(16.0f);
        book.setBookCategory(BookCategory.NON_FICTION);
        Book book2 = new Book();
        book2.setArticleId(2);
        book2.setName("Entwickeln von Web-Anwendungen");
        book2.setPrice(23.0f);
        book2.setBookCategory(BookCategory.POPULAR_SCIENCE);

        Cart cart = new Cart();
        Integer articleQuantity = 1;
        cart.addCartItem(book, articleQuantity);
        cart.addCartItem(book2, articleQuantity);

        Order order = new Order();
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setPositionId(1);
        orderPosition.setArticleId(book.getArticleId());
//        orderPosition.setArticlePrice(book.getPrice());
        orderPosition.setArticleQuantity(1);

        order.addOrderPosition(orderPosition);
        order.setOrderId("TEST ORDER ID");

        customer = new Customer(cart);
        customer.addOrder(order.getOrderId());
        customer.setAddress("Test address");
        customer.setName("Test");
    }

    @AfterAll
    static void afterAll() {
        customer = null;
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void canCustomerBeAdded() {
        Customer addedCustomer = customerService.addCustomerToDatabase(customer);
        Assertions.assertNotNull(addedCustomer);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void canCustomerBeFound() {
        Customer foundCustomer = customerService.findCustomerByCustomerId(customer.getCustomerId());
        System.out.println("Found customer with name: " + foundCustomer.getName());
        List<CartItem> customerCartItems = customer.getCart().getCartItems();
        System.out.println("Cart items: ");
        for (CartItem cartItem : customerCartItems) {
            System.out.println("article id: " + cartItem.getArticleId() +
                    ", quantity: " + cartItem.getQuantity() + ", price: " + cartItem.getPrice());
        }
        List<String> orderIDs = foundCustomer.getOrderIDs();
        System.out.println("Order IDs: ");
        for (String orderID : orderIDs) {
            System.out.println("id: " + orderID);
        }
        Assertions.assertNotNull(foundCustomer);
    }


    // TODO: customerDeletion needs to be worked on
    @Test
    @org.junit.jupiter.api.Order(3)
    void canCustomerBeDeleted() {
        Customer deletedCustomer = customerService.deleteCustomerByCustomerId(customer.getCustomerId());
        System.out.println("Deleted customer with name: " + deletedCustomer.getName());
        Assertions.assertNotNull(deletedCustomer);
    }


}
