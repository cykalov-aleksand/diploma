package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Ad {
 @Schema(description = "id автора объявления")
 private    int author;
 @Schema(description = "ссылка на картинку объявления")
 private    String image;
 @Schema(description = "id объявления")
 private    int pk;
 @Schema(description = "заголовок объявления")
 private    String title;
 @Schema(description = "цена объявления")
 private int price;
public Ad(){}
 public Ad(int author, String image, int pk, String title, int price) {
  this.author = author;
  this.image = image;
  this.pk = pk;
  this.title = title;
  this.price = price;
 }

 public int getAuthor() {
  return author;
 }

 public void setAuthor(int author) {
  this.author = author;
 }

 public String getImage() {
  return image;
 }

 public void setImage(String image) {
  this.image = image;
 }

 public int getPk() {
  return pk;
 }

 public void setPk(int pk) {
  this.pk = pk;
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
}
