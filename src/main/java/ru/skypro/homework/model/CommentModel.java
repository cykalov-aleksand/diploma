package ru.skypro.homework.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Getter
@Table(name = "comment_model")
@Entity
public class CommentModel {
    @Setter
    @Id
    @GeneratedValue
    private Integer pk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AdModel adModel;
//private int authorId;
    @Setter
    @Column(nullable = false, name = "created_at")
private long createdAt;
@Setter
private String text;
public CommentModel(){}
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof CommentModel that)) return false;
        return Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pk);
    }

    @JsonIgnore
    public void setAdModel(AdModel adModel) {
        this.adModel = adModel;
    }

    @Override
    public String toString() {
        return "CommentModel{" +
                "pk=" + pk +
                ", adModel=" + adModel +
                ", createdAt=" + createdAt +
                ", text='" + text + '\'' +
                '}';
    }
}




