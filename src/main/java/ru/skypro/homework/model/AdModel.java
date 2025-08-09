package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ad_model")
public class AdModel {
    @Setter
    @Getter
    @Id
    @GeneratedValue
    private Integer pk;
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private UserModel userModel;
    //private int authorId;
    @Setter
    @Getter
    @Column(nullable = false, name = "image")
    private String image;
    @Setter
    @Getter
    private String title;
    @Setter
    @Getter
    private int price;
    @Setter
    @Getter
    private String description;
    @OneToMany(mappedBy = "adModel")
    private List<CommentModel>commentModels;
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

    @JsonIgnore
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public String toString() {
        return "AdModel{" +
                "pk=" + pk +
                ", userModel=" + userModel +
                ", imageId='" + image + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}

