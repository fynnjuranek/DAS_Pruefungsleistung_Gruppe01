package de.leuphana.shop.structure.sales;

import de.leuphana.shop.structure.article.Article;

import java.util.*;

public class Cart {
	private Integer id;
	private List<CartItem> cartItems;
	private int numberOfArticles;

	public Cart() {
		cartItems = new ArrayList<>();
		numberOfArticles = 0;
	}

	public void addCartItem(Article article) {
		CartItem cartItem = null;
		for (CartItem cartItemEntity : cartItems) {
			if (Objects.equals(cartItemEntity.getArticleId(), article.getArticleId())) {
				cartItem = cartItems.get(cartItems.indexOf(cartItemEntity));
				cartItem.incrementQuantity();
			}
		}
		if (cartItem == null) {
			cartItem = new CartItem();
			cartItem.setArticleId(article.getArticleId());
			cartItem.setPrice(article.getPrice());
			cartItem.incrementQuantity();
		}
		cartItems.add(cartItem);
		numberOfArticles++;
	}

	public void deleteCartItem(int articleId) {
		for (CartItem cartItem : cartItems) {
			if (cartItem.getArticleId() == (articleId)) {
				cartItems.remove(cartItem.getCartItemId());
				break;
			}
		}

	}

	public void decrementArticleQuantity(Integer articleId) {
		for (CartItem cartItem : cartItems) {
			if (Objects.equals(cartItem.getArticleId(), articleId)) {
				cartItem.decrementQuantity();
				if (cartItem.getQuantity() <= 0)
					cartItems.remove(cartItem);
				numberOfArticles--;
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumberOfArticles(int numberOfArticles) {
		this.numberOfArticles = numberOfArticles;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public int getNumberOfArticles() {
		return numberOfArticles;
	}

//	public double getTotalPrice() {
//		double totalPrice = 0.0;
//
//		for (CartItem cartItem : getCartItems()) {
//			totalPrice += cartItem.getQuantity() * cartItem.getPrice();
//		}
//
//		return totalPrice;
//	}

}