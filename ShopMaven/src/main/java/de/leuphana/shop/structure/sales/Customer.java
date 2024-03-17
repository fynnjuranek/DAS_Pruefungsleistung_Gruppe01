package de.leuphana.shop.structure.sales;

import java.util.HashMap;
import java.util.Map;

public class Customer {
	private static Integer lastGeneratedCustomerId = 0;

	private Integer customerId;
	private String name;
	private String address;
	private Cart cart;
	private Map<String, Order> orders;

	public Customer(Cart cart) {
		this.customerId = ++lastGeneratedCustomerId;
		this.cart = cart;
		orders = new HashMap<String, Order>();
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Cart getCart() {
		return cart;
	}
	
	public void addOrder(Order order) {
		orders.put(order.getOrderId(), order);
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

	public Map<String, Order> getOrders() {
		return orders;
	}

	public void setOrders(Map<String, Order> orders) {
		this.orders = orders;
	}
}