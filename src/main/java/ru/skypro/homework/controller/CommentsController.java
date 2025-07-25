package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.CommentsService;

//@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Комментарии")
public class CommentsController {
    private CommentsService commentsService;
    @Tag(name = "Комментарии")
    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode ="200",content = { @Content(schema = @Schema(implementation = Comments.class), mediaType = "application/json") }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Not fount", responseCode ="404",content = { @Content(schema = @Schema()) })
    })
    public Comments getCommentsOnAd(@RequestParam("ID продукта")int id) {
        return commentsService.getCommentsOnAd(id);
    }
    @Tag(name = "Комментарии")
    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавления комментария к объявлению")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode ="200",content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json") }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Not fount", responseCode ="404",content = { @Content(schema = @Schema()) })
    })
    public Comment addCommentToAd(@RequestParam("ID продукта")int id, @RequestBody String text) {
        return commentsService.addCommentToAd(id,text);
    }
     @Tag(name = "Комментарии")
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode ="200",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Forbidden", responseCode = "403",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Not fount", responseCode ="404",content = { @Content(schema = @Schema()) })
    })
    public Void deleteCommentToAdId(@RequestParam("ID продукта")int adId,int commentId) {
        return commentsService.deleteCommentToAdId(adId,commentId);
    }
    @Tag(name = "Комментарии")
    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode ="200",content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json") }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Forbidden", responseCode = "403",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Not fount", responseCode ="404",content = { @Content(schema = @Schema()) })
    })
    public Comment updatingComment(@RequestParam("ID продукта")int adId, int commentId, @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return commentsService.updatingComment(adId,commentId,createOrUpdateComment);
    }
   }
