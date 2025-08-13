package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
@Getter
@Setter
@Entity
@Table(name = "ad_model")
public class AdModel {
     @Id
    @GeneratedValue
    private Integer pk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private UserModel userModel;
    @Column(nullable = false, name = "image")
    private String image;
    private String title;
    @Size(max = 10000000)
    private int price;
    private String description;
    @OneToMany(mappedBy = "adModel")
    private List<CommentModel>commentModels;
    public AdModel(){}

    @Size(max = 10000000)
    public int getPrice() {
        return price;
    }

    public void setPrice(@Size(max = 10000000) int price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AdModel)) return false;
        AdModel adModel = (AdModel) object;
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


}

