package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class CreateOrUpdateAd {
    @Size(min=4,max = 32)
    @Schema(description = "заголовок объявления")
    private String title;
    @Size(min=8,max = 64)
    @Schema(description = "описание объявления")
    private String description;
     @Min(value = 0)
    @Max(value =  10000000)
     @Schema(description = "цена объявления")
      private int price;
}
