package de.leuphana.connector;

import de.leuphana.article.behaviour.ArticleService;
import de.leuphana.shop.structure.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleRestConnectorProvider {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/getArticle/{name}")
    public Article findArticleByName(@PathVariable("name") String name) {
        return articleService.findArticleByName(name);
    }

    @RequestMapping("/getArticle/{articleId}")
    public Article findArticleByArticleId(@PathVariable("articleId") Integer articleId) {
        return articleService.findArticleById(articleId);
    }

    @RequestMapping("/getArticles")
    public List<Article> findAllArticles() {
        return articleService.findAllArticles();
    }

    @PostMapping("/addArticle")
    public Article addArticleToDatabase(@RequestBody Article article) {
        return articleService.addArticleToDatabase(article);
    }

    @RequestMapping("/deleteArticle/{name}")
    public Article deleteArticleByName(@PathVariable("name") String name) {
        return articleService.deleteArticleByName(name);
    }



}
