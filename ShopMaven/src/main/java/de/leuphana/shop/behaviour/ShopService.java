package de.leuphana.shop.behaviour;

import de.leuphana.connector.ArticleRestConnectorRequester;
import de.leuphana.shop.structure.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    ArticleRestConnectorRequester articleRestRequester;

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
}
