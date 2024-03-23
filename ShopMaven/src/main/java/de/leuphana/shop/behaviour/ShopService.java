package de.leuphana.shop.behaviour;

import de.leuphana.connector.ArticleRestConnectorRequester;
import de.leuphana.connector.CustomerRestConnectorRequester;
import de.leuphana.connector.OrderJMSConnectorSender;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.billing.Invoice;
import de.leuphana.shop.structure.billing.InvoicePosition;
import de.leuphana.shop.structure.sales.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    ArticleRestConnectorRequester articleRestConnectorRequester;

    @Autowired
    OrderJMSConnectorSender orderJMSConnectorSender;

    @Autowired
    CustomerRestConnectorRequester customerRestConnectorRequester;

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

    public Order getOrder(String orderId) {
        return orderJMSConnectorSender.getOrder(orderId);
    }

    public Order deleteOrder(String orderId) {
        return orderJMSConnectorSender.deleteOrder(orderId);
    }

    public Integer createCustomer(String customerName, String customerAddress) {
        Customer customer = new Customer(customerName, customerAddress);
        Customer savedCustomer = customerRestConnectorRequester.addCustomer(customer);
        return savedCustomer.getCustomerId();
    }

    public Customer getCustomer(Integer customerId) {
        return customerRestConnectorRequester.getCustomerByCustomerId(customerId);
    }

    public Cart addArticleToCart(Integer customerId, Integer articleId, Integer quantity) {
        Article foundArticle = getArticleByArticleId(articleId);
        Customer customer = getCustomer(customerId);
        Cart cart = customer.getCart();
        // TODO: maybe add this to customer service
        cart.addCartItem(foundArticle, quantity);

        // update customer
        customer.setCart(cart);
        // TODO: change updateCart to addArticleToCart() method in CustomerService
        Customer updatedCustomer = customerRestConnectorRequester.updateCart(cart, customerId);
//        Customer updatedCustomer = customerRestConnectorRequester.addCustomer(customer);
        return updatedCustomer.getCart();
    }

    public Order checkOutCart(int customerId) {
        Customer customer = customerRestConnectorRequester.getCustomerByCustomerId(customerId);
        System.out.println(customer.getCustomerId());
        Cart foundCart = customer.getCart();
        Order order = orderJMSConnectorSender.createOrder();
        System.out.println(order.getOrderId());
        for (CartItem item : foundCart.getCartItems()) {
            order = orderJMSConnectorSender.addOrder(item.getArticleId(), item.getQuantity(), order.getOrderId());
        }

        customer = customerRestConnectorRequester.addOrderToCustomer(customerId, order.getOrderId());
//        System.out.println(customer.getCart().getCartItems().size()); // Immer noch null!
        return order;
    }

    public Invoice createInvoice(String orderId) {

        Invoice invoice = new Invoice();
        Order order = orderJMSConnectorSender.getOrder(orderId);

        for (OrderPosition orderPosition : order.getOrderPositions()) {

            InvoicePosition invoicePosition = new InvoicePosition();
            invoicePosition.setArticleId(orderPosition.getArticleId());
            // TODO: add articlePrice again
//			invoicePosition.setArticlePrice(orderPosition.getArticlePrice());
            invoicePosition.setArticleQuantity(orderPosition.getArticleQuantity());

            invoice.addInvoicePosition(invoicePosition);
        }

        return invoice;
    }

}
