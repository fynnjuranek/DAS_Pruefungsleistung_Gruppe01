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

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTests {

    @Autowired
    CustomerService customerService;

    static Customer customer;
    static Customer addedCustomer;

    static Book book;

    @BeforeAll
    static void beforeAll() {
        book = new Book();
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
        addedCustomer = null;
        book = null;
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void canCustomerBeAdded() {
        addedCustomer = customerService.addCustomerToDatabase(customer);
        System.out.println("Added customer with id: " + addedCustomer.getCustomerId() + " to customer database");
        System.out.println("Customer name: " + addedCustomer.getName() + ", address: " + addedCustomer.getAddress());
        Assertions.assertNotNull(addedCustomer);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void canCustomerBeFound() {
        Customer foundCustomer = customerService.findCustomerByCustomerId(addedCustomer.getCustomerId());
        System.out.println("Found customer with name: " + foundCustomer.getName());
        List<CartItem> customerCartItems = foundCustomer.getCart().getCartItems();
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

    @Test
    @org.junit.jupiter.api.Order(3)
    void canArticleBeAddedToCart() {
        CD cd = new CD();
        cd.setArticleId(3);
        cd.setArtist("Vampire Weekend");
        cd.setPrice(13.0f);
        cd.setName("Vampire Weekend");
        cd.setManufacturer("Test producer");
        Integer articleQuantity = 5;

        Customer customer = customerService.addArticleToCart(addedCustomer.getCustomerId(), cd, articleQuantity);

        System.out.println("New cart: ");
        for (CartItem cartItem : customer.getCart().getCartItems()) {
            System.out.println(cartItem.getArticleId());
            System.out.println(cartItem.getPrice());
        }
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void canArticleBeRemovedFromCart() {
        Customer  foundCustomer = customerService.removeArticleFromCart(addedCustomer.getCustomerId(), book.getArticleId());
        boolean isRemoved = true;
        for (CartItem cartItem : foundCustomer.getCart().getCartItems()) {
            if (cartItem.getArticleId().equals(book.getArticleId())) {
                System.out.println(cartItem.getArticleId());
                isRemoved = false;
                break;
            }
        }
        System.out.println("Article with id: " + book.getArticleId() + " has been removed from cart of customer id: " + addedCustomer.getCustomerId());
        System.out.println("(variable) isRemoved: " + isRemoved);
        Assertions.assertTrue(isRemoved);
    }


    @Test
    @org.junit.jupiter.api.Order(5)
    void canCustomerBeDeleted() {
        boolean isCustomerDeleted = customerService.deleteCustomerByCustomerId(addedCustomer.getCustomerId());
        System.out.println("Deleted customer with name: " + addedCustomer.getName() + " got deleted? " + isCustomerDeleted);
        Assertions.assertTrue(isCustomerDeleted);
    }


}
