package de.leuphana.connector;

import de.leuphana.order.behaviour.OrderService;
import de.leuphana.shop.structure.sales.Order;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderJMSConnectorProvider {

    @Autowired
    OrderService orderService;

    @JmsListener(destination = "addOrder")
    public Order addOrder(int articleId, Message message) throws JMSException {
        int articleQuantity = message.getIntProperty("articleQuantity");
        // TODO: debugging
        System.out.println("Order added: " + articleId);
        Order order = orderService.addNewOrderToDatabase(articleId, articleQuantity);
        return order;
    }

    @JmsListener(destination = "getOrder")
    public Order getOrder(String orderId) throws JMSException {
        return orderService.findOrderById(orderId);
    }

    @JmsListener(destination = "deleteOrder")
    public Order deleteOrder(String orderId) throws JMSException {
        return orderService.deleteOrderById(orderId);
    }
}
