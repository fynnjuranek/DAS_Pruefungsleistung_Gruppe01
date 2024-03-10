package de.leuphana.customer.entity;

import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.sales.CartItem;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    private Map<Integer, CartItemEntity> cartItems;
    private int numberOfArticles;

    public CartEntity() {
        cartItems = new HashMap<Integer, CartItemEntity>();
        numberOfArticles = 0;
    }

    public void addCartItem(int articleId) {
        CartItemEntity cartItem;
        if (cartItems.containsKey(articleId)) {
            cartItem = cartItems.get(articleId);
            cartItem.incrementQuantity();
        } else {
            cartItem = new CartItemEntity();
            cartItems.put(articleId, cartItem);
        }
        numberOfArticles++;
    }

    public void deleteCartItem(int articleId) {
        for (CartItemEntity cartItem : cartItems.values()) {
            if (cartItem.getArticleId() == (articleId)) {
                cartItems.remove(cartItem.getCartItemId());
                break;
            }
        }
    }

    public void decrementArticleQuantity(int articleId) {
        if (cartItems.containsKey(articleId)) {
            CartItemEntity cartItem = (CartItemEntity) cartItems.get(articleId);
            cartItem.decrementQuantity();

            if (cartItem.getQuantity() <= 0)
                cartItems.remove(articleId);

            numberOfArticles--;
        }
    }

    public Collection<CartItemEntity> getCartItems() {
        return cartItems.values();
    }

    public int getNumberOfArticles() {
        return numberOfArticles;
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;

        for (CartItemEntity cartItem : getCartItems()) {
            totalPrice += cartItem.getQuantity() * cartItem.getPrice();
        }

        return totalPrice;
    }

}
