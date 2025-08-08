package ru.skypro.homework.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
@Table(name = "comment_model")
@Entity
public class CommentModel {
    @Id
    @GeneratedValue
    private Integer pk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AdModel adModel;
//private int authorId;
    @Column(nullable = false, name = "created_at")
private long createdAt;
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

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }
    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AdModel getAdModel() {
        return adModel;
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




