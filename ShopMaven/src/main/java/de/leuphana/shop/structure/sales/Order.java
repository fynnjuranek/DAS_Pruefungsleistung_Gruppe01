package de.leuphana.shop.structure.sales;

import de.leuphana.shop.structure.article.Article;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Integer orderId;
	private Integer customerId;
	private List<OrderPosition> orderPositions;

	public Order() {
		orderPositions = new ArrayList<OrderPosition>();
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderId() {
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

	public int getNumberOfArticles() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getTotalPrice() {
		double totalPrice = 0.0;

		for (OrderPosition orderPosition : orderPositions) {
			totalPrice += orderPosition.getArticleQuantity() * orderPosition.getArticlePrice();
		}

		return totalPrice;
	}

}