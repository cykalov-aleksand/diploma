package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class NewPassword {
    @Size(min=8,max = 16)
    @Schema(description = "текущий пароль")
    private String currentPassword;
    @Size(min=8,max = 16)
    @Schema(description = "новый пароль")
    private String newPassword;
}
