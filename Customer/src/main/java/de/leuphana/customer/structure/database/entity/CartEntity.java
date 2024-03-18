package de.leuphana.customer.structure.database.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.ALL)
    private List<CartItemEntity> cartItems;

    private int numberOfArticles;

    public CartEntity() {
        cartItems = new ArrayList<>();
        numberOfArticles = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCartItems(List<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public void setNumberOfArticles(int numberOfArticles) {
        this.numberOfArticles = numberOfArticles;
    }

    // TODO: delete code which is business information
    public void addCartItem(int articleId) {
        CartItemEntity cartItem = null;
        for (CartItemEntity cartItemEntity : cartItems) {
            if (cartItemEntity.getArticleId() == articleId) {
                cartItem = cartItems.get(cartItems.indexOf(cartItemEntity));
                cartItem.incrementQuantity();
                cartItem.setCartEntity(this);
            }
        }
        if (cartItem == null) {
            cartItem = new CartItemEntity();
            cartItem.setArticleId(articleId);
            cartItem.incrementQuantity();
            cartItem.setCartEntity(this);
        }
        cartItems.add(cartItem);
        numberOfArticles++;
    }

    public void deleteCartItem(int articleId) {
        for (CartItemEntity cartItem : cartItems) {
            if (cartItem.getArticleId() == (articleId)) {
                cartItems.remove(cartItem.getCartItemId());
                break;
            }
        }
    }

    public void decrementArticleQuantity(int articleId) {
        if (cartItems.get(articleId) != null) {
            CartItemEntity cartItem = (CartItemEntity) cartItems.get(articleId);
            cartItem.decrementQuantity();

            if (cartItem.getQuantity() <= 0)
                cartItems.remove(articleId);

            numberOfArticles--;
        }
    }

    public List<CartItemEntity> getCartItems() {
        return cartItems;
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
