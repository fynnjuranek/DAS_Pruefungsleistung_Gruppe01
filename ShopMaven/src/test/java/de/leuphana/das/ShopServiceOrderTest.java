package de.leuphana.das;

import de.leuphana.shop.behaviour.ShopService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShopServiceOrderTest {

    @Autowired
    ShopService shopService;

    static int articleId;
    static int articleQuantity;

    @BeforeAll
    public static void setUp() {
        articleId = 1;
        articleQuantity = 2;
    }

    @Test
    void canOrderBeAdded() {
        de.leuphana.shop.structure.sales.Order order = shopService.addOrder(articleId, articleQuantity);
        System.out.println("Added article with id: " + articleId + " and quantity: " + articleQuantity + " to order with id: " + order.getOrderId());
        Assertions.assertNotNull(order);
    }

}
