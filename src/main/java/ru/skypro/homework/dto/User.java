package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Schema(description = "id пользователя")
    private int id;
       @Schema(description = "логин пользователя")
    private String email;
    @Schema(description = "имя пользователя")
    private String firstName;
    @Schema(description = "фамилия пользователя")
    private String lastName;
    @Schema(description = "телефон пользователя")
    private String phone;
    @Schema(description = "ссылка на аватар пользователя")
    private String image;
    @Schema(description = "роль пользователя")
    private Role role;
}
