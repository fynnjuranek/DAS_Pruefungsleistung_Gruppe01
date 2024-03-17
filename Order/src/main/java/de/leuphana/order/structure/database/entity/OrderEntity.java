package de.leuphana.order.structure.database.entity;

import de.leuphana.shop.structure.sales.OrderPosition;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class OrderEntity {

    @Id
    private String orderId;
    // TODO: CustomerID needs to be deleted i think. The connection should be in customer with "orderID"
//    private Integer customerId;
    private List<OrderPosition> orderPositions;

    public OrderEntity() {
        orderPositions = new ArrayList<OrderPosition>();
    }

//    public int getCustomerId() {
//        return customerId;
//    }

//    public void setCustomerId(Integer customerId) {
//        this.customerId = customerId;
//    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public void addOrderPosition(OrderPosition orderPosition) {
        this.orderPositions.add(orderPosition);
    }
}
