package de.leuphana.article.structure.database.entity;

import jakarta.persistence.Entity;

@Entity
public class BookEntity extends ArticleEntity {
    private String author;

    public BookEntity() {
    }

    public BookEntity(String manufacturer, String name, float price, String author) {
        super(manufacturer, name, price);
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
