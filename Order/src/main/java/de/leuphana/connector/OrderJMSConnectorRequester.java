package de.leuphana.connector;

import de.leuphana.order.behaviour.OrderService;
import de.leuphana.shop.structure.sales.Order;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderJMSConnectorRequester {

    // This is the requester because it attaches it's self to the queue (destination)
    // and reacts to a message which is provided by the shopSystem

    @Autowired
    OrderService orderService;

    @JmsListener(destination = OrderJMSConnectorProvider.ADD_ORDER)
    public Order addOrder(int articleId, Message message) throws JMSException {
        int articleQuantity = message.getIntProperty(OrderJMSConnectorProvider.ARTICLE_QUANTITY);
        // TODO: debugging
        System.out.println("Order added: " + articleId);
        Order order = orderService.addNewOrderToDatabase(articleId, articleQuantity);
        return order;
    }

    @JmsListener(destination = OrderJMSConnectorProvider.GET_ORDER)
    public Order getOrder(String orderId) throws JMSException {
        return orderService.findOrderById(orderId);
    }

    @JmsListener(destination = OrderJMSConnectorProvider.DELETE_ORDER)
    public Order deleteOrder(String orderId) throws JMSException {
        return orderService.deleteOrderById(orderId);
    }
}
