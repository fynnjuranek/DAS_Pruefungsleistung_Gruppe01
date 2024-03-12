package de.leuphana.order.structure.database.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer orderId;
    private List<OrderPositionEntity> orderPositions;
    public OrderEntity() {
        orderPositions = new ArrayList<OrderPositionEntity>();
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "fk_orderId", referencedColumnName = "orderId")
    public List<OrderPositionEntity> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPositionEntity> orderPositions) {
        this.orderPositions = orderPositions;
    }
}
