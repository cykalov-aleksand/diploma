package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.model.dto.Login;
import ru.skypro.homework.model.dto.Register;
import ru.skypro.homework.service.AuthService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @Tag(name = "Авторизация")
    @Operation(summary = "Авторизация пользователя")
    @ApiResponses(value = {
            @ApiResponse( description = "OK",responseCode = "200",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        if (authService.login(login.getUsername(), login.getPassword())) {
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @Tag(name = "Регистрация")
    @Operation(summary = "Регистрация пользователя")
       @ApiResponses(value = {
            @ApiResponse(description = "Created", responseCode = "201",content = { @Content(schema = @Schema()) }),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register register) {
        if (authService.register(register)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
