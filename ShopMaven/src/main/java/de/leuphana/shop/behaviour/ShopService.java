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
    public Article addNewArticleToCatalog(Article article) {
        return articleRestConnectorRequester.addArticle(article);
    }

    public Article deleteArticleByName(String name) {
        return articleRestConnectorRequester.deleteArticleByName(name);
    }

    public Order deleteOrder(String orderId) {
        return orderJMSConnectorSender.deleteOrder(orderId);
    }

    public Integer createCustomer(String customerName, String customerAddress) {
        Customer createdCustomer = customerRestConnectorRequester.createCustomer(customerName, customerAddress);
        return createdCustomer.getCustomerId();
    }

    public Customer getCustomer(Integer customerId) {
        return customerRestConnectorRequester.getCustomerByCustomerId(customerId);
    }

    public Customer addArticleToCart(Integer customerId, Integer articleId, Integer quantity) {
        Article foundArticle = getArticleByArticleId(articleId);
        return customerRestConnectorRequester.addArticleToCart(customerId, foundArticle, quantity);
    }

    public Customer removeArticleFromCart(Integer customerId, Integer articleId) {
        return customerRestConnectorRequester.removeArticleFromCart(customerId, articleId);
    }

    public Customer decrementArticleQuantityInCart(Integer customerId, Integer articleId) {
        return customerRestConnectorRequester.decrementArticleQuantityInCart(customerId, articleId);
    }
    public Order checkOutCart(int customerId) {
        Customer customer = customerRestConnectorRequester.getCustomerByCustomerId(customerId);

        Order order = orderJMSConnectorSender.createOrder();
        for (CartItem item : customer.getCart().getCartItems()) {
            order = orderJMSConnectorSender.addOrderPosition(item.getArticleId(), item.getQuantity(), order.getOrderId());
        }

        customerRestConnectorRequester.addOrderToCustomer(customerId, order.getOrderId());
        return order;
    }

    public Invoice createInvoice(String orderId) {

        Invoice invoice = new Invoice();
        Order order = orderJMSConnectorSender.getOrder(orderId);

        for (OrderPosition orderPosition : order.getOrderPositions()) {

            InvoicePosition invoicePosition = new InvoicePosition();
            invoicePosition.setArticleId(orderPosition.getArticleId());

            // Retrieve articlePrice from ArticleDatabase, because order shouldn't know anything about articles
            Float articlePrice = getArticleByArticleId(orderPosition.getArticleId()).getPrice();
			invoicePosition.setArticlePrice(articlePrice);
            invoicePosition.setArticleQuantity(orderPosition.getArticleQuantity());

            invoice.addInvoicePosition(invoicePosition);
        }

        return invoice;
    }

}
