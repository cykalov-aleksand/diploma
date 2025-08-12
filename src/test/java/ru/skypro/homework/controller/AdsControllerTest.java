package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdService adService;

    @Test
    @WithMockUser
    void getInformationAboutAdAuthorizedTest() throws Exception {
        int id = 1;
        when(adService.getInformationAboutAd(id)).thenReturn(new ExtendedAd());
        mockMvc.perform(get("/ads/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getInformationAboutAdNoAuthorizedTest() throws Exception {
        int id = 1;
        when(adService.getInformationAboutAd(id)).thenReturn(new ExtendedAd());
        mockMvc.perform(get("/ads/{id}", id))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void getAdsFromAuthorizedTest() throws Exception {
        when(adService.getAdsFromAuthorized()).thenReturn(new Ads());
        mockMvc.perform(get("/ads/me"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAdsFromNoAuthorizedTest() throws Exception {
        when(adService.getAdsFromAuthorized()).thenReturn(new Ads());
        mockMvc.perform(get("/ads/me"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void getAllAdsAuthorizedTest() throws Exception {
        when(adService.getAllService()).thenReturn(new Ads());
        mockMvc.perform(get("/ads"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void updatingInformationAboutAdAuthorizedTest() throws Exception {
        int id = 1;
        CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd();
        createOrUpdateAd.setTitle("title");
        createOrUpdateAd.setDescription("description");
        createOrUpdateAd.setPrice(100);

        when(adService.updatingInformationAboutAd(eq(id), any(CreateOrUpdateAd.class))).thenReturn(new Ad());

        mockMvc.perform(patch("/ads/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createOrUpdateAd)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updatingInformationAboutAdNoAuthorizedTest() throws Exception {
        int id = 1;
        CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd();
        mockMvc.perform(patch("/ads/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createOrUpdateAd)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void updatingAdImageAuthorizedTest() throws Exception {
        int id = 1;
        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", MediaType.IMAGE_JPEG_VALUE, "image".getBytes());

        when(adService.UpdatingAdImage(eq(id), any())).thenReturn(ResponseEntity.ok(""));

        mockMvc.perform(multipart("/ads/{id}/image", id)
                        .file(image)
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        }))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void addingAdAuthorizedTest() throws Exception {
        CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd();
        createOrUpdateAd.setTitle("title");
        createOrUpdateAd.setDescription("description");
        createOrUpdateAd.setPrice(100);

        MockMultipartFile properties = new MockMultipartFile("parameters", "", MediaType.APPLICATION_JSON_VALUE, objectMapper.writeValueAsString(createOrUpdateAd).getBytes());
        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", MediaType.IMAGE_JPEG_VALUE, "image".getBytes());

        when(adService.addingAd(any(CreateOrUpdateAd.class), any())).thenReturn(ResponseEntity.status(201).body(new Ad()));

        mockMvc.perform(multipart("/ads")
                        .file(properties)
                        .file(image))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
