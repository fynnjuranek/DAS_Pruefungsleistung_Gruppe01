package de.leuphana.shop.structure.sales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
	private static Integer lastGeneratedCustomerId = 0;

	private Integer customerId;
	private String name;
	private String address;
	private Cart cart;
	private List<String> orderIDs;

	public Customer() {}

	public Customer(Cart cart) {
		this.customerId = ++lastGeneratedCustomerId;
		this.cart = cart;
		orderIDs = new ArrayList<String>();
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Cart getCart() {
		return cart;
	}
	
	public void addOrder(Order order) {
		orderIDs.add(order.getOrderId());
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

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<String> getOrderIDs() {
		return orderIDs;
	}

	public void setOrders(List<String> orderIDs) {
		this.orderIDs = orderIDs;
	}
}