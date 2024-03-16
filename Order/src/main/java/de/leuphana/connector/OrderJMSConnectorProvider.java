package de.leuphana.connector;

import de.leuphana.order.behaviour.OrderService;
import de.leuphana.shop.structure.sales.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderJMSConnectorProvider {
    // TODO: Implement JMS-Connector

    @Autowired
    OrderService orderService;

    @JmsListener(destination = "addOrder")
    public Order addOrder(int articleId) {
        System.out.println("Order added: " + articleId);
// TODO: quantity needs to be implemented
        return orderService.addNewOrderToDatabase(articleId, 1);
    }
}
