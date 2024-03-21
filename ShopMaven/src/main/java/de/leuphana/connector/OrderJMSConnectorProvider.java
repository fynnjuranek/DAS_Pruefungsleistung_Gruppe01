package de.leuphana.connector;

import de.leuphana.shop.structure.sales.Order;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TemporaryQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrderJMSConnectorProvider {
    // TODO: Maybe the name needs to be changed!

    // This is the provider because it sends the message to the queue,
    // no matter what or who is attached to the destination

    // Destinations
    public static final String ADD_ORDER = "addOrder";
    public static final String GET_ORDER = "getOrder";
    public static final String DELETE_ORDER = "deleteOrder";
    public static final String CREATE_ORDER = "createOrder";
    // Properties
    public static final String ARTICLE_QUANTITY = "articleQuantity";
    public static final String ORDER_ID= "orderId";

    @Autowired
    JmsTemplate jmsTemplate;

    public Order addOrder(int articleId, int articleQuantity, String orderId) {
        Order respondedOrder = jmsTemplate.execute(session -> {
            TemporaryQueue tempQueue = session.createTemporaryQueue();
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws JMSException {
                    message.setIntProperty(OrderJMSConnectorProvider.ARTICLE_QUANTITY, articleQuantity);
                    message.setStringProperty(OrderJMSConnectorProvider.ORDER_ID, orderId);
                    message.setJMSReplyTo(tempQueue);
                    return message;
                }
            };
            jmsTemplate.convertAndSend(OrderJMSConnectorProvider.ADD_ORDER, articleId, messagePostProcessor);
            return (Order) jmsTemplate.receiveAndConvert(tempQueue);
        });
        return respondedOrder;
    }

    public Order createOrder() {
        return jmsTemplate.execute(session -> {
            TemporaryQueue tempQueue = session.createTemporaryQueue();
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws JMSException {
                    message.setJMSReplyTo(tempQueue);
                    return message;
                }
            };
            jmsTemplate.convertAndSend(OrderJMSConnectorProvider.CREATE_ORDER, 0, messagePostProcessor);
            return (Order) jmsTemplate.receiveAndConvert(tempQueue);
        });
    }

    public Order getOrder(String orderId) {
        Order foundOrder = jmsTemplate.execute(session -> {
            TemporaryQueue tempQueue = session.createTemporaryQueue();
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws JMSException {
                    message.setJMSReplyTo(tempQueue);
                    return message;
                }
            };
            jmsTemplate.convertAndSend(OrderJMSConnectorProvider.GET_ORDER, orderId, messagePostProcessor);
            return (Order) jmsTemplate.receiveAndConvert(tempQueue);
        });
        return foundOrder;
    }

    public Order deleteOrder(String orderId) {
        Order deletedOrder = jmsTemplate.execute(session -> {
            TemporaryQueue tempQueue = session.createTemporaryQueue();
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws JMSException {
                    message.setJMSReplyTo(tempQueue);
                    return message;
                }
            };
            jmsTemplate.convertAndSend(OrderJMSConnectorProvider.DELETE_ORDER, orderId, messagePostProcessor);
            return (Order) jmsTemplate.receiveAndConvert(tempQueue);
        });
        return deletedOrder;
    }
}
