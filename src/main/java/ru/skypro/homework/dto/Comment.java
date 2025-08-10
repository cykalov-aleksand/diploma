package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Comment {
  @Schema(description = "id автора комментария")
  private   int author;
  @Schema(description = "ссылка на аватар автора комментария")
  private   String authorImage;
  @Schema(description = "имя создателя комментария")
  private   String authorFirstName;
  @Schema(description = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970")
  private   long createdAt;
  @Schema(description = "id комментария")
  private   int pk;
  @Schema(description = "текст комментария")
  private   String text;
  public Comment(){}
    public Comment(int author, String authorImage, String authorFirstName, long createdAt, int pk, String text) {
    this.author = author;
    this.authorImage = authorImage;
    this.authorFirstName = authorFirstName;
    this.createdAt = createdAt;
    this.pk = pk;
    this.text = text;
  }

  public int getAuthor() {
    return author;
  }

  public void setAuthor(int author) {
    this.author = author;
  }

  public String getAuthorImage() {
    return authorImage;
  }

  public void setAuthorImage(String authorImage) {
    this.authorImage = authorImage;
  }

  public String getAuthorFirstName() {
    return authorFirstName;
  }

  public void setAuthorFirstName(String authorFirstName) {
    this.authorFirstName = authorFirstName;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }

  public int getPk() {
    return pk;
  }

  public void setPk(int pk) {
    this.pk = pk;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
