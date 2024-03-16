package de.leuphana.shop.behaviour;

import com.netflix.discovery.converters.Auto;
import de.leuphana.connector.ArticleRestConnectorRequester;
import de.leuphana.connector.OrderJMSConnectorRequester;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.sales.Order;
import org.apache.catalina.servlets.DefaultServlet;
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

    public Order addOrder(int articleId) {
        return orderJMSConnectorRequester.addOrder(articleId);
    }

    // Method-layout:
    /*
    getOrderForCustomer()
    getArticle()
    addArticleToOrder() (only ArticleID)
     */

    public Article deleteArticleByName(String name) {
        return articleRestRequester.deleteArticleByName(name);
    }
}
