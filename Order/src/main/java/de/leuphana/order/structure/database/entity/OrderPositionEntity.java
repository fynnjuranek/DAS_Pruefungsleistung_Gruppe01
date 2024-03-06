package de.leuphana.order.structure.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class OrderPositionEntity {

    @Id
    @GeneratedValue
    private Integer positionId;
    private Integer articleId;
    private Float articlePrice;
    private int articleQuantity;

    public Integer getPositionId() {
        return positionId;
    }
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Float getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(Float articlePrice) {
        this.articlePrice = articlePrice;
    }

    public int getArticleQuantity() {
        return articleQuantity;
    }

    public void setArticleQuantity(int articleQuantity) {
        this.articleQuantity = articleQuantity;
    }

}
