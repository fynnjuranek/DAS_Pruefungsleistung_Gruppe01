package de.leuphana.connector;

import de.leuphana.shop.structure.article.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("Article")
public interface ArticleRestConnectorRequester {

    @PostMapping("/addArticle")
    Article addArticle(@RequestBody Article article);

    @RequestMapping("/getArticle/{name}")
    Article getArticleByName(@PathVariable("name") String name);

    @RequestMapping("/deleteArticle/{name}")
    Article deleteArticleByName(@PathVariable("name") String name);

}
