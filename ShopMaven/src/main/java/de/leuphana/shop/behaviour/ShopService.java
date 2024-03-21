package de.leuphana.shop.behaviour;

import de.leuphana.connector.ArticleRestConnectorRequester;
import de.leuphana.connector.CustomerRestConnectorRequester;
import de.leuphana.connector.OrderJMSConnectorProvider;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.BookCategory;
import de.leuphana.shop.structure.sales.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    ArticleRestConnectorRequester articleRestConnectorRequester;

    @Autowired
    OrderJMSConnectorProvider orderJMSConnectorProvider;

    @Autowired
    CustomerRestConnectorRequester customerRestConnectorRequester;

    public Article getArticleByName(String name) {
        return articleRestConnectorRequester.getArticleByName(name);
    }

    public Article getArticleByArticleId(int articleId) {
        return articleRestConnectorRequester.getArticleByArticleId(articleId);
    }

    public List<Article> getArticles() {
        return articleRestConnectorRequester.getArticles();
    }
    public Article addArticle(Article article) {
        return articleRestConnectorRequester.addArticle(article);
    }

    public Article deleteArticleByName(String name) {
        return articleRestConnectorRequester.deleteArticleByName(name);
    }

    public Order addOrder(int articleId, int articleQuantity) {
//        return orderJMSConnectorProvider.addOrder(articleId, articleQuantity, order.getOrderId());

        // TODO: add orderID to customers
        return null;
    }

    public Order getOrder(String orderId) {
        return orderJMSConnectorProvider.getOrder(orderId);
    }

    public Order deleteOrder(String orderId) {
        return orderJMSConnectorProvider.deleteOrder(orderId);
    }

    public void addArticleToCart(Integer customerId, Integer articleId) {
        Article foundArticle = getArticleByArticleId(articleId);
        Customer customer = customerRestConnectorRequester.getCustomerByCustomerId(customerId);
        Cart cart = customerRestConnectorRequester.getCustomerByCustomerId(customerId).getCart();
        // TODO: maybe add this to customer service
        cart.addCartItem(foundArticle);
        // update customer
        customer.setCart(cart);
        Customer updatedCustomer = customerRestConnectorRequester.addCustomer(customer);

    }

    public Order checkOutCart(int customerId) {
        Customer customer = customerRestConnectorRequester.getCustomerByCustomerId(customerId);
        System.out.println(customer.getCustomerId());
        Cart foundCart = customer.getCart();
        Order order = orderJMSConnectorProvider.createOrder();
        System.out.println(order.getOrderId());
        for (CartItem item : foundCart.getCartItems()) {
            order = orderJMSConnectorProvider.addOrder(item.getArticleId(), item.getQuantity(), order.getOrderId());
        }

        customer = customerRestConnectorRequester.addOrderToCustomer(customerId, order.getOrderId());
//        System.out.println(customer.getCart().getCartItems().size()); // Immer noch null!
        return order;
    }

    // Method-layout:
    /*
    getOrderForCustomer()
    getArticle()
    addArticleToOrder() (only ArticleID)
     */

}
