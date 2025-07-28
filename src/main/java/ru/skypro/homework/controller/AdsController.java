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
import ru.skypro.homework.model.CreateOrUpdateAd;
import ru.skypro.homework.model.dto.Ad;
import ru.skypro.homework.model.dto.Ads;
import ru.skypro.homework.model.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления")
public class AdsController {
  private final AdService adService;

    @Tag(name = "Объявления")
    @GetMapping()
    @Operation(summary = "Получение всех объявлений")
    @ApiResponse( description = "Ok",responseCode ="200", content = {@Content(schema = @Schema(implementation = Ads.class), mediaType = "application/json")})
    public Optional<Ads> getAllAds() {
        return Optional.of(adService.getAllService());
    }
    @Tag(name = "Объявления")
    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    @ApiResponses(value = {
            @ApiResponse( description = "Ok",responseCode ="200",content = { @Content(schema = @Schema(implementation = ExtendedAd.class), mediaType = "application/json") }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Not fount", responseCode ="404",content = { @Content(schema = @Schema()) })
    })
    public ExtendedAd getInformationAboutAd(@RequestParam("ID продукта")int id) {
       return adService.getInformationAboutAd(id);

    }
    @Tag(name = "Объявления")
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    @ApiResponses(value = {
            @ApiResponse( description = "No content",responseCode ="204",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Unauthorized", responseCode = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Forbidden", responseCode = "403",content = { @Content(schema = @Schema()) }),
            @ApiResponse( description = "Not fount", responseCode = "404",content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<Void> deleteAd(@RequestParam("ID продукта")int id) {
        return adService.deleteAd(id);
    }
    @Tag(name = "Объявления")
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "Ok",description = "200",content = { @Content(schema = @Schema(implementation = Ad.class), mediaType = "application/json") }),
            @ApiResponse( responseCode = "Unauthorized", description = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( responseCode = "Forbidden", description = "403",content = { @Content(schema = @Schema()) }),
            @ApiResponse( responseCode = "Not fount", description = "404",content = { @Content(schema = @Schema()) })
    })
    public Ad updatingInformationAboutAd(@RequestParam("ID продукта")int id,@RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return adService.updatingInformationAboutAd(id,createOrUpdateAd);
    }
    @Tag(name = "Объявления")
    @GetMapping("/ads/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "Ok", description = "200",content = { @Content(schema = @Schema(implementation = Ads.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "Unauthorized", description = "401", content = {@Content(schema = @Schema())})
    })
    Ads getAdsFromAuthorized() {
        return adService.getAdsFromAuthorized();
    }
    @Tag(name = "Объявления")
    @PatchMapping(value = "/{id}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "Ok",description = "200",content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/octet-stream") }),
            @ApiResponse( responseCode = "Unauthorized", description = "401",content = { @Content(schema = @Schema()) }),
            @ApiResponse( responseCode = "Forbidden", description = "403",content = { @Content(schema = @Schema()) }),
            @ApiResponse( responseCode = "Not fount", description = "404",content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<String> UpdatingAdImage(@PathVariable int id,@RequestParam MultipartFile image)throws IOException {
        if(image.getSize()>=1024*300) {
            return ResponseEntity.status(401).build();
        }
        adService.UpdatingAdImage(id,image);
        return ResponseEntity.ok().build();
    }
       @Tag(name = "Объявления")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "Created",description = "201",content = { @Content(schema = @Schema(implementation = Ad.class), mediaType = "application/json") }),
            @ApiResponse( responseCode = "Unauthorized", description = "401",content = { @Content(schema = @Schema()) })
               })
    public Ad addingAd(@RequestBody CreateOrUpdateAd createOrUpdateAd,@RequestParam MultipartFile image)throws IOException {
       return adService.addingAd(createOrUpdateAd,image);
         }
   }
