package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserServices;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
  @Tag(name = "Пользователи")
public class UsersController {
    private  UserServices userServices;

    @Tag(name = "Пользователи")
    @PostMapping("/set_password")
    @Operation(summary = "обновления пароля")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode = "200",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Forbidden", responseCode = "403",content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<?> passwordUpdates(@RequestBody NewPassword newPassword) {
       return userServices.passwordUpdates(newPassword);
        }
    @Tag(name = "Пользователи")
    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode = "200",content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) })
                })
    public ResponseEntity<User> getInformationAboutUser() {
        return userServices.getInformationAboutUser();
    }
    @Tag(name = "Пользователи")
    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode = "200",content = { @Content(schema = @Schema(implementation = UpdateUser.class), mediaType = "application/json") }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<UpdateUser> updatingUserInformation(@RequestBody UpdateUser updateUser) {
        return userServices.updatingUserInformation(updateUser);
    }
      @Tag(name = "Пользователи")
     @PatchMapping(value = "/me/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode = "200",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<Void> updatingUsersAvatar( @RequestParam MultipartFile image)throws IOException {
        if(image.getSize()>=1024*300) {
            return ResponseEntity.status(401).build();
        }
        userServices.updatingUsersAvatar(image);
        return ResponseEntity.ok().build();
    }
        }

