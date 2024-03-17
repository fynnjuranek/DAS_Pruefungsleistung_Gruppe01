package de.leuphana.das;

import de.leuphana.shop.behaviour.ShopService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShopServiceOrderTest {

    @Autowired
    ShopService shopService;

    @Test
    void canOrderBeAdded() {
        de.leuphana.shop.structure.sales.Order orderID = shopService.addOrder(1);
        System.out.println(orderID);
        Assertions.assertNotNull(orderID);
    }

}
