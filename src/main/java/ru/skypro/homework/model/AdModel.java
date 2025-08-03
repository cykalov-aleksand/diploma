package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ad_model")
public class AdModel {
    @Id
    @GeneratedValue
    private Integer pk;
    @Column(nullable = false, name = "author_id")
    private int authorId;
    @Column(nullable = false, name = "image_id")
    private String imageId;
    private String title;
    private int price;
    private String description;
    public AdModel(){}

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AdModel adModel)) return false;
        return Objects.equals(pk, adModel.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pk);
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AdModel{" +
                "pk=" + pk +
                ", authorId=" + authorId +
                ", imageAd='" + imageId + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}

