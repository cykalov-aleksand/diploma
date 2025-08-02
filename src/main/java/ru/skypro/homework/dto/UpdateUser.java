package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;



@Data
public class UpdateUser {
    @Schema(description = "имя пользователя",minLength = 3,maxLength = 10)
    private String firstName;
    @Schema(description = "фамилия пользователя",minLength = 3,maxLength = 10)
    private String lastName;
    @Pattern(regexp ="\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(description = "телефон пользователя",example = "+7 (695) 498-51-37")
    private String phone;
}
