package de.leuphana.customer.structure.database.entity;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String name;
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

    // TODO: change Orders to orderID (only reference)
    @ElementCollection
    private List<Integer> orderIDs;

    public CustomerEntity() {
    }

    public CustomerEntity(String name, String address, CartEntity cartEntity, List<Integer> orders) {
        this.name = name;
        this.address = address;
        this.cartEntity = cartEntity;
        this.orderIDs = orders;
    }


    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CartEntity getCartEntity() {
        return cartEntity;
    }

    public void setCartEntity(CartEntity cartEntity) {
        this.cartEntity = cartEntity;
    }

    public List<Integer> getOrderIDs() {
        return orderIDs;
    }

    public void setOrderIDs(List<Integer> orderIDs) {
        this.orderIDs = orderIDs;
    }

    public void addOrder(int orderID) {
        this.orderIDs.add(orderID);
    }

}
