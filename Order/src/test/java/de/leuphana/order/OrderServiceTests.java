package de.leuphana.order;

import de.leuphana.order.behaviour.OrderService;
import de.leuphana.order.structure.database.entity.OrderEntity;
import de.leuphana.order.structure.database.mapper.OrderMapper;
import de.leuphana.shop.structure.sales.OrderPosition;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTests {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMapper orderMapper;

    static de.leuphana.shop.structure.sales.Order order;

    @Test
    void contextLoads() {
    }

    // TODO: implement tests
    @Test
    @Order(1)
    void canOrderBeMapped() {
        OrderEntity orderEntity = new OrderEntity();
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setArticleId(1);
//        orderPositionEntity.setArticlePrice(16.0f);
        orderPosition.setArticleQuantity(2);
//        OrderPosition orderPosition = orderMapper.mapToOrderPosition(orderPositionEntity);
        orderEntity.addOrderPosition(orderPosition);
//        orderEntity.setCustomerId(1);
        order = orderMapper.mapToOrder(orderEntity);
        Assertions.assertEquals(orderEntity.getOrderPositions(), order.getOrderPositions());
    }

    @Test
    @Order(2)
    void canOrderPositionBeMapped() {

    }

    @Test
    @Order(3)
    void canOrderBeAdded() {
        int articleId = 1;
        int articleQuantity = 2;
        de.leuphana.shop.structure.sales.Order savedOrder = orderService.addNewOrderToDatabase(articleId, articleQuantity);
//        System.out.println("Added order: " + savedOrder.getOrderId() + " " + savedOrder.getCustomerId() + " to database");
        Assertions.assertNotNull(savedOrder);
    }

    @Test
    @Order(4)
    void canOrderBeFound() {

    }

    @Test
    @Order(5)
    void canOrderBeDeleted() {

    }

}
