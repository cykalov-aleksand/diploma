package ru.skypro.homework.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<String> handleForbiddenException
    (ForbiddenException e) {
        // Возвращаем статус 403
        return ResponseEntity.status(403).build();
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException
            (NotFoundException e) {
        // Возвращаем статус 404
        return ResponseEntity.status(404).build();
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException
            (UnauthorizedException e) {
        // Возвращаем статус 401
        return ResponseEntity.status(401).build();
    }
}
