package de.leuphana.shop.behaviour;

import de.leuphana.connector.ArticleRestConnectorRequester;
import de.leuphana.connector.OrderJMSConnectorRequester;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.sales.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    ArticleRestConnectorRequester articleRestRequester;

    @Autowired
    OrderJMSConnectorRequester orderJMSConnectorRequester;

    public Article getArticleByName(String name) {
        return articleRestRequester.getArticleByName(name);
    }

    public List<Article> getArticles() {
        return articleRestRequester.getArticles();
    }
    public Article addArticle(Article article) {
        return articleRestRequester.addArticle(article);
    }

    public Article deleteArticleByName(String name) {
        return articleRestRequester.deleteArticleByName(name);
    }

    public Order addOrder(int articleId, int articleQuantity) {
        return orderJMSConnectorRequester.addOrder(articleId, articleQuantity);

        // TODO: add orderID to customers

    }

    public Order getOrder(String orderId) {
        return orderJMSConnectorRequester.getOrder(orderId);
    }

    public Order deleteOrder(String orderId) {
        return orderJMSConnectorRequester.deleteOrder(orderId);
    }

    // Method-layout:
    /*
    getOrderForCustomer()
    getArticle()
    addArticleToOrder() (only ArticleID)
     */

}
