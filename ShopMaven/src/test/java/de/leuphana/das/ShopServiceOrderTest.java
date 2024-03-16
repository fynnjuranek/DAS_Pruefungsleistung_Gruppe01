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
        Assertions.assertNotNull(shopService.addOrder(1));
    }

}
