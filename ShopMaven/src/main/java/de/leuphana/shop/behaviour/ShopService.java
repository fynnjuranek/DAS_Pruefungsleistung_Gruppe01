package de.leuphana.shop.behaviour;

import de.leuphana.connector.ArticleRestConnectorRequester;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    ArticleRestConnectorRequester articleRestRequester;

    public Book addBook(Book book) {
        return articleRestRequester.addBook(book);
    }

    public CD addCD(CD cd) {
        return articleRestRequester.addCD(cd);
    }

    public Book getBook(String name) {
        return articleRestRequester.getBook(name);
    }
}
