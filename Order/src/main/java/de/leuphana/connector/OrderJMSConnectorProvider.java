package de.leuphana.connector;

import de.leuphana.order.behaviour.OrderService;
import de.leuphana.shop.structure.sales.Order;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderJMSConnectorProvider {
    // TODO: Implement JMS-Connector

    @Autowired
    OrderService orderService;

    @JmsListener(destination = "addOrder")
    public Order addOrder(int articleId, Message message) throws JMSException {
        int articleQuantity = message.getIntProperty("articleQuantity");
        System.out.println("Order added: " + articleId);
        Order order = orderService.addNewOrderToDatabase(articleId, articleQuantity);
        return order;
    }
}
