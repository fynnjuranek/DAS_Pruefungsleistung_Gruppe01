package de.leuphana.order.behaviour;

import de.leuphana.order.structure.database.OrderDatabase;
import de.leuphana.order.structure.database.mapper.OrderMapper;
import de.leuphana.shop.structure.sales.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDatabase orderDatabase;

    @Autowired
    OrderMapper orderMapper;

    // TODO: implement OrderService
    public Order addOrderToDatabase(Order order) {
        return null;
    }

    public Order findOrderById(Integer orderID) {
        return null;
    }

    public List<Order> findAllOrders() {
        return null;
    }

    public Order deleteOrderById(Integer orderId) {
        return null;
    }

}
