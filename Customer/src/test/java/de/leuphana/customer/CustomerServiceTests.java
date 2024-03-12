package de.leuphana.customer;

import de.leuphana.customer.behaviour.CustomerService;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.sales.Cart;
import de.leuphana.shop.structure.sales.Customer;
import de.leuphana.shop.structure.sales.Order;
import de.leuphana.shop.structure.sales.OrderPosition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
        Cart cart = new Cart();
        cart.addCartItem(book);

        Order order = new Order();
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setPositionId(1);
        orderPosition.setArticleId(book.getArticleId());
        orderPosition.setArticlePrice(book.getPrice());
        orderPosition.setArticleQuantity(1);

        order.addOrderPosition(orderPosition);

        customer = new Customer(cart);
        customer.addOrder(order);
        customer.setAddress("Test address");
        customer.setName("Test");
    }

    @AfterAll
    static void afterAll() {
        customer = null;
    }

    @Test
    void canCustomerBeAdded() {
        Customer addedCustomer = customerService.addCustomerToDatabase(customer);
        Assertions.assertNotNull(addedCustomer);
    }


}
