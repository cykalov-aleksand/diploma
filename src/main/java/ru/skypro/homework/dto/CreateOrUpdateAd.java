package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;



@Data
public class CreateOrUpdateAd {
    @Schema(description = "заголовок объявления",minLength = 4,maxLength = 32)
    private String title;
    @Schema(description = "описание объявления",minLength = 8,maxLength = 64)
    private String description;
   @Schema(description = "цена объявления",minimum = "0",maximum = "10000000")
      private int price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
