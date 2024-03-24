package de.leuphana.das.connector;

import de.leuphana.connector.OrderJMSConnectorSender;
import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.sales.Customer;
import de.leuphana.shop.structure.sales.OrderPosition;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderJMSConnectorSenderTest {

    @Autowired
    ShopService shopService;

    @Autowired
    OrderJMSConnectorSender orderJMSConnectorSender;


    static int articleId;
    static int articleQuantity;
    static Customer savedCustomer;
    static de.leuphana.shop.structure.sales.Order addedOrder;
    static de.leuphana.shop.structure.sales.Order createdOrder;
    @Autowired
    private JmsTemplate jmsTemplate;

    @BeforeAll
    public static void setUp() {
        articleId = 1;
        articleQuantity = 2;
    }

    @Test
    @Order(1)
    void canOrderBeCreated() {
        createdOrder = orderJMSConnectorSender.createOrder();
        Assertions.assertNotNull(createdOrder);
    }

    @Test
    @Order(2)
    void canOrderBeAdded() {
        addedOrder = orderJMSConnectorSender.addOrderPosition(articleId, articleQuantity, createdOrder.getOrderId());
        System.out.println("Added article with id: " + articleId + " and quantity: " + articleQuantity + " to order with id: " + addedOrder.getOrderId());
        Assertions.assertNotNull(addedOrder);
    }

    @Test
    @Order(3)
    void canOrderBeFound() {
        de.leuphana.shop.structure.sales.Order foundOrder = orderJMSConnectorSender.getOrder(addedOrder.getOrderId());
        System.out.println("Found order with id: " + foundOrder.getOrderId() + " and number of articles: " + foundOrder.getNumberOfArticles());
        List<OrderPosition> orderPositions = foundOrder.getOrderPositions();
        System.out.println("Articles: ");
        for (OrderPosition orderPosition : orderPositions) {
            System.out.println("id: " + orderPosition.getArticleId() + " quantity: " + orderPosition.getArticleQuantity());
        }
        Assertions.assertNotNull(foundOrder);
    }

    @Test
    @Order(4)
    void canOrderBeDeleted() {
        de.leuphana.shop.structure.sales.Order deletedOrder = orderJMSConnectorSender.deleteOrder(addedOrder.getOrderId());
        System.out.println("Deleted order with id: " + deletedOrder.getOrderId());
        Assertions.assertNotNull(deletedOrder);
    }
//
//    @Test
//    @Order(4)
//    void canCheckOutCartBeCreated() {
//        // TODO: TESTING
//        Book book = new Book();
//        book.setArticleId(1);
//        book.setName("THINKING, FAST AND SLOW");
//        book.setManufacturer("Penguin Verlag");
//        book.setAuthor("Steven Pinker");
//        book.setPrice(16.0f);
//        Book book2 = new Book();
//        book2.setArticleId(2);
//        book2.setName("Entwickeln von Web-Anwendungen");
//        book2.setPrice(23.0f);
//        book2.setBookCategory(BookCategory.POPULAR_SCIENCE);
//
//        Cart cart = new Cart();
//        cart.addCartItem(book, 3);
//        cart.addCartItem(book2, 2);
//
//        Customer createdCustomer = new Customer(cart);
////        createdCustomer.addOrder(order);
//        createdCustomer.setAddress("Test address");
//        createdCustomer.setName("Test");
//        savedCustomer = customerRestConnectorRequester.addCustomer(createdCustomer);
//        System.out.println(savedCustomer.getAddress());
//        de.leuphana.shop.structure.sales.Order order = shopService.checkOutCart(savedCustomer.getCustomerId());
////        customerRestConnectorRequester.addOrderToCustomer()
////        System.out.println(order.getOrderId());
//        Assertions.assertNotNull(order);
//    }

//    @Test
//    @Order(5)
//    void canCustomerOrderBeAdded() {
////        customerRestConnectorRequester.addOrderToCustomer(1);
//    }

//    @Test
//    @Order(6)
//    void canCustomerBeAdded() {
//        Customer customer = new Customer();
//        customerRestConnectorRequester.addCustomer(customer);
//    }
//
//    @Test
//    @Order(7)
//    void canArticleBeAddedToCart() {
//        CD cd = new CD();
//        cd.setArtist("Vampire Weekend");
//        cd.setPrice(13.0f);
//        cd.setName("Vampire Weekend");
//        cd.setManufacturer("Test producer");
//        Integer articleQuantity = 5;
//        Article addedArticle = shopService.addArticle(cd);
//
//        shopService.addArticleToCart(savedCustomer.getCustomerId(), addedArticle.getArticleId(), articleQuantity);
//
//    }




}