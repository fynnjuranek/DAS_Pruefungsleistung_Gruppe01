package de.leuphana.order;

import com.netflix.discovery.converters.Auto;
import de.leuphana.order.structure.database.OrderDatabase;
import de.leuphana.order.structure.database.entity.OrderEntity;
import de.leuphana.order.structure.database.entity.OrderPositionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderApplicationTests {

    @Autowired
    OrderDatabase orderDatabase;

    @Test
    void contextLoads() {
    }

    @Test
    void canOrderBeAdded() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerId(1);
        OrderEntity order = orderDatabase.save(orderEntity);
        System.out.println(order.getOrderId() + " " + order.getCustomerId());
    }

}
