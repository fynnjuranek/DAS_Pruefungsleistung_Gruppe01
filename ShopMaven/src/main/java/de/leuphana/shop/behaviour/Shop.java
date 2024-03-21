package de.leuphana.shop.behaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.billing.Invoice;
import de.leuphana.shop.structure.billing.InvoicePosition;
import de.leuphana.shop.structure.sales.ArticleComplaintRequest;
import de.leuphana.shop.structure.sales.Cart;
import de.leuphana.shop.structure.sales.CartItem;
import de.leuphana.shop.structure.sales.Catalog;
import de.leuphana.shop.structure.sales.Customer;
import de.leuphana.shop.structure.sales.Order;
import de.leuphana.shop.structure.sales.OrderPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class Shop {
	private Catalog catalog;
	private Map<Integer, Customer> customers;

	public Shop() {
		customers = new HashMap<Integer, Customer>();
		catalog = new Catalog();
	}

	public Integer createCustomerWithCart() {
		Cart cart = new Cart();

		Customer customer = new Customer(cart);

		customers.put(customer.getCustomerId(), customer);

		return customer.getCustomerId();
	}

	public Article getArticle(int articleId) {
		// Delegation
		return catalog.getArticle(articleId);
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void removeArticleFromCart(Integer customerId, int articleId) {
		// Delegation
		Cart cart = customers.get(customerId).getCart();

		cart.deleteCartItem(articleId);
	}

	public void addArticleToCart(Integer customerId, Integer articleId) {
		Article foundArticle = getArticle(articleId);

		Cart cart = customers.get(customerId).getCart();

		cart.addCartItem(foundArticle);
	}

	public void decrementArticleQuantityInCart(Integer customerId,
			Integer articleId) {
		Cart cart = customers.get(customerId).getCart();

		cart.decrementArticleQuantity(articleId);
	}

	public Order checkOutCart(int customerId) {

		Customer customer = customers.get(customerId);
		Cart cart = customer.getCart();

		Order order = new Order();
//		order.setOrderId(1);


		int i = 1;
		
		List<OrderPosition> orderPositions = new ArrayList<OrderPosition>();

		for (CartItem cartItem : cart.getCartItems()) {
			OrderPosition orderPosition = new OrderPosition();
			orderPosition.setPositionId(i++);
			orderPosition.setArticleId(cartItem.getArticleId());
			orderPosition.setArticleQuantity(cartItem.getQuantity());
			orderPositions.add(orderPosition);
		}
		order.setOrderPositions(orderPositions);

		customer.addOrder(order.getOrderId());

		return order;
	}
	
	public void complainArticle(ArticleComplaintRequest articleComplaint) {
		System.out.println(articleComplaint.toString());
	}

	
	public Cart getCartForCustomer(Integer customerId) {
		return customers.get(customerId).getCart();
	}
	
	// =============== billing service interface =================
	
	public Invoice createInvoice(Order order) {
		
		Invoice invoice = new Invoice();
		for (OrderPosition orderPosition : order.getOrderPositions()) {
			
			InvoicePosition invoicePosition = new InvoicePosition();
			invoicePosition.setArticleId(orderPosition.getArticleId());
//			invoicePosition.setArticlePrice(orderPosition.getArticlePrice());
			invoicePosition.setArticleQuantity(orderPosition.getArticleQuantity());
			
			invoice.addInvoicePosition(invoicePosition);
		}
		
		return invoice;
	}
}