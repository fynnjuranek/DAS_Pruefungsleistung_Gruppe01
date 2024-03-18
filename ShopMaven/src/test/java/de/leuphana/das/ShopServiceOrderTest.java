package de.leuphana.das;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.sales.OrderPosition;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShopServiceOrderTest {

    @Autowired
    ShopService shopService;

    static int articleId;
    static int articleQuantity;
    static de.leuphana.shop.structure.sales.Order addedOrder;

    @BeforeAll
    public static void setUp() {
        articleId = 1;
        articleQuantity = 2;
    }

    @Test
    @Order(1)
    void canOrderBeAdded() {
        addedOrder = shopService.addOrder(articleId, articleQuantity);
        System.out.println("Added article with id: " + articleId + " and quantity: " + articleQuantity + " to order with id: " + addedOrder.getOrderId());
        Assertions.assertNotNull(addedOrder);
    }

    @Test
    @Order(2)
    void canOrderBeFound() {
        de.leuphana.shop.structure.sales.Order foundOrder = shopService.getOrder(addedOrder.getOrderId());
        System.out.println("Found order with id: " + foundOrder.getOrderId() + " and number of articles: " + foundOrder.getNumberOfArticles());
        List<OrderPosition> orderPositions = foundOrder.getOrderPositions();
        System.out.println("Articles: ");
        for (OrderPosition orderPosition : orderPositions) {
            System.out.println("id: " + orderPosition.getArticleId() + " quantity: " + orderPosition.getArticleQuantity());
        }
        Assertions.assertNotNull(foundOrder);
    }

    @Test
    @Order(3)
    void canOrderBeDeleted() {
        de.leuphana.shop.structure.sales.Order deletedOrder = shopService.deleteOrder(addedOrder.getOrderId());
        System.out.println("Deleted order with id: " + deletedOrder.getOrderId());
        Assertions.assertNotNull(deletedOrder);
    }


}
