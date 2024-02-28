package de.leuphana.connector;

import de.leuphana.article.behaviour.ArticleService;
import de.leuphana.shop.structure.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleRestConnectorProvider {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/getArticle/{name}")
    public Article findArticleByName(@PathVariable("name") String name) {
        return articleService.findArticleByName(name);
    }

    @PostMapping("/addArticle")
    public Article addArticleToDatabase(@RequestBody Article article) {
        return articleService.addArticleToDatabase(article);
    }

}
