package de.leuphana.order.structure.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class OrderEntity {

    @Id
    private String orderId;
    private List<OrderPositionEntity> orderPositions;

    public OrderEntity() {
        orderPositions = new ArrayList<OrderPositionEntity>();
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderPositionEntity> getOrderPositionEntities() {
        return orderPositions;
    }

    public void setOrderPositionEntities(List<OrderPositionEntity> orderPositions) {
        this.orderPositions = orderPositions;
    }

//    public void addOrderPosition(OrderPosition orderPosition) {
//        this.orderPositions.add(orderPosition);
//    }
}
