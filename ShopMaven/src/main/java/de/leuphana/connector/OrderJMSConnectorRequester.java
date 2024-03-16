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
public class OrderJMSConnectorRequester {
    // TODO: implement JMS-Requester

    @Autowired
    JmsTemplate jmsTemplate;

    public Order addOrder(int articleId) {
        Order respondedOrder = jmsTemplate.execute(session -> {
            TemporaryQueue tempQueue = session.createTemporaryQueue();
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws JMSException {
                    message.setJMSReplyTo(tempQueue);
                    return message;
                }
            };
            jmsTemplate.convertAndSend("addOrder", articleId, messagePostProcessor);
            return (Order) jmsTemplate.receiveAndConvert(tempQueue);
        });
        return respondedOrder;
    }
}
