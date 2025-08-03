package ru.skypro.homework.model;


import jakarta.persistence.*;

import java.util.Objects;
@Table(name = "comment_model")
@Entity
public class CommentModel {
    @Id
    @GeneratedValue
    private Integer pk;
    @Column(nullable = false, name = "author_id")
private int authorId;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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

    @Override
    public String toString() {
        return "CommentModel{" +
                "pk=" + pk +
                ", authorId=" + authorId +
                ", createdAt=" + createdAt +
                ", text='" + text + '\'' +
                '}';
    }
}




