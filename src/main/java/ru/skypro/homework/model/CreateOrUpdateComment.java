package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateOrUpdateComment {
    @Schema(description = "имя пользователя",minLength = 8,maxLength = 64)
    private String text;
}
