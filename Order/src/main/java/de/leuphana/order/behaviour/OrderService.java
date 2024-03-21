package de.leuphana.order.behaviour;

import de.leuphana.order.structure.database.OrderDatabase;
import de.leuphana.order.structure.database.entity.OrderEntity;
import de.leuphana.order.structure.database.mapper.OrderMapper;
import de.leuphana.shop.structure.sales.Order;
import de.leuphana.shop.structure.sales.OrderPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDatabase orderDatabase;

    @Autowired
    OrderMapper orderMapper;

    // TODO: implement OrderService
    public Order addOrderToDatabase(Order order) {
        OrderEntity orderEntity = orderMapper.mapToOrderEntity(order);
        OrderEntity savedOrderEntity = orderDatabase.save(orderEntity);
        return orderMapper.mapToOrder(savedOrderEntity);
    }

    // TODO: I think there needs to be an implementation for List<OrderEntity> with all Orders for a customer

    public Order addNewOrderToDatabase(String orderId, Integer articleId, int articleQuantity) {
        OrderEntity orderEntity = orderDatabase.findOrderEntityByOrderId(orderId);
        // TODO: change this
        // create new order if it doesn't already exist
        if (orderEntity == null) {
            orderEntity = new OrderEntity();
            // TODO: delete customerID
//            orderEntity.setCustomerId(customerId);
        }
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setArticleId(articleId);
//        orderPosition.setArticlePrice(articlePrice);
        orderPosition.setArticleQuantity(articleQuantity);
        orderEntity.addOrderPosition(orderPosition);

        return orderMapper.mapToOrder(orderDatabase.save(orderEntity));
    }

    public Order createNewOrder() {
        OrderEntity orderEntity = new OrderEntity();
        return orderMapper.mapToOrder(orderDatabase.save(orderEntity));
    }

    public Order findOrderById(String orderID) {
        OrderEntity foundOrderEntity = orderDatabase.findById(orderID).get();
        return orderMapper.mapToOrder(foundOrderEntity);
    }

    public List<Order> findAllOrders() {
        List<Order> orders = new ArrayList<>();
        for (OrderEntity orderEntity : orderDatabase.findAll()) {
            orders.add(orderMapper.mapToOrder(orderEntity));
        }
        return orders;
    }

    public Order deleteOrderById(String orderId) {
        OrderEntity deletedOrder = orderDatabase.deleteOrderEntityByOrderId(orderId);
        return orderMapper.mapToOrder(deletedOrder);
    }

}
