package de.leuphana.connector;

import de.leuphana.shop.structure.article.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("Article")
public interface ArticleRestConnectorRequester {

    // TODO: add more functionality!
    @PostMapping("/addBook")
    Book addBook(@RequestBody Book book);

    @RequestMapping("/getBook{name}")
    Book getBook(@PathVariable String name);



}
