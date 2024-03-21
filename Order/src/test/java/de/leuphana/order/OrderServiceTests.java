package de.leuphana.order;

import de.leuphana.order.behaviour.OrderService;
import de.leuphana.order.structure.database.entity.OrderEntity;
import de.leuphana.order.structure.database.mapper.OrderMapper;
import de.leuphana.shop.structure.sales.OrderPosition;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTests {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMapper orderMapper;

    static de.leuphana.shop.structure.sales.Order order;

    @Test
    @Order(1)
    void canOrderBeAdded() {
        int articleId = 1;
        int articleQuantity = 2;
        order = orderService.createNewOrder();
        order = orderService.addNewOrderToDatabase(order.getOrderId(), articleId, articleQuantity);
        System.out.println("Added order with id: " + order.getOrderId());
        Assertions.assertNotNull(order);
    }
    // TODO: implement tests
    @Test
    @Order(2)
    void canOrderBeMapped() {
//        OrderEntity orderEntity = new OrderEntity();
//        OrderPosition orderPosition = new OrderPosition();
//        orderPosition.setArticleId(1);
//        orderPositionEntity.setArticlePrice(16.0f);
//        orderPosition.setArticleQuantity(2);
//        OrderPosition orderPosition = orderMapper.mapToOrderPosition(orderPositionEntity);
//        orderEntity.addOrderPosition(orderPosition);
//        orderEntity.setCustomerId(1);
        OrderEntity mappedOrderEntity = orderMapper.mapToOrderEntity(order);
        Assertions.assertEquals(order.getOrderId(), mappedOrderEntity.getOrderId());
    }

    @Test
    @Order(3)
    void canOrderPositionBeMapped() {

    }


    @Test
    @Order(4)
    void canOrderBeFound() {
        de.leuphana.shop.structure.sales.Order foundOrder = orderService.findOrderById(order.getOrderId());
        System.out.println("Found order with id: " + foundOrder.getOrderId() + " and number of articles: " + foundOrder.getNumberOfArticles());
        List<OrderPosition> orderPositions = foundOrder.getOrderPositions();
        System.out.println("Articles: ");
        for (OrderPosition orderPosition : orderPositions) {
            System.out.println("id: " + orderPosition.getArticleId() + " quantity: " + orderPosition.getArticleQuantity());
        }
        Assertions.assertNotNull(foundOrder);
    }

    @Test
    @Order(5)
    void canOrderBeDeleted() {
        de.leuphana.shop.structure.sales.Order deletedOrder = orderService.deleteOrderById(order.getOrderId());
        System.out.println("Deleted order with id: " + deletedOrder.getOrderId());
        Assertions.assertNotNull(deletedOrder);
    }

}
