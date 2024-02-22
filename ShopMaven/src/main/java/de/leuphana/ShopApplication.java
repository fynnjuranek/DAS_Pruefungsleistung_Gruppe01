package de.leuphana;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.article.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
// TODO: Add DiscoveryClients
public class ShopApplication implements ApplicationRunner {

    @Autowired
    ShopService shopService;

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Book book = new Book();
        book.setAuthor("Test");
        book.setManufacturer("Test");
//        Book book = shopService.getBook("The C++ Programming Language");
        shopService.addBook(book);
        System.out.println(book.getAuthor() + " " + book.getName());
    }
}
