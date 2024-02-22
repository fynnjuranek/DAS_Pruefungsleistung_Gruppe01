package de.leuphana.connector;

import de.leuphana.article.behaviour.ArticleService;
import de.leuphana.shop.structure.article.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleRestConnectorProvider {

    @Autowired
    ArticleService articleService;

    // TODO: add the whole attributes to url with openFeign
    @PostMapping("/addBook")
    public Book addBookToDatabase(@RequestBody Book book) {
        return articleService.addBookToDatabase(book);
    }

    @RequestMapping("/getBook{name}")
    public Book findArticleByName(@PathVariable("name") String name) {
        return articleService.findBookByName(name);
    }




}
