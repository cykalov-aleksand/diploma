package ru.skypro.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyles.title;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

public class AdsControllerTestCorrect {

    private MockMvc mockMvc;

    @Mock
    private AdService adService;

    @InjectMocks
    private AdsController adsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adsController).build();
    }

    @Test
    public void testGetInformationAboutAd_ValidId() throws Exception {
        int validId = 1;
        when(adService.getInformationAboutAd(validId)).thenReturn(new ExtendedAd());

        mockMvc.perform(get("/ads/{id}", validId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetInformationAboutAd_InvalidId() throws Exception {
        int invalidId = 999;
        when(adService.getInformationAboutAd(invalidId)).thenReturn(null);

        mockMvc.perform(get("/ads/{id}", invalidId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllAds() throws Exception {
        when(adService.getAllService()).thenReturn(new Ads());

        mockMvc.perform(get("/ads")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAd_ValidData() throws Exception {
        String adJson = "{"title":"Test Ad","description":"Test Description","price":100}";

        mockMvc.perform(post("/ads")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateAd_InvalidData() throws Exception {
        String adJson = "{"title":"","description":"","price":0}";

        mockMvc.perform(post("/ads")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateAd_ValidData() throws Exception {
        int validId = 1;
        String adJson = "{"title":"Updated Ad","description":"Updated Description","price":150}";

        mockMvc.perform(put("/ads/{id}", validId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(adJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateAd_InvalidData() throws Exception {
        int invalidId = 999;
        String adJson = "{"title":"Updated Ad","description":"Updated Description","price":150}";

        mockMvc.perform(put("/ads/{id}", invalidId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(adJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteAd_ValidId() throws Exception {
        int validId = 1;

        mockMvc.perform(delete("/ads/{id}", validId))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAd_InvalidId() throws Exception {
        int invalidId = 999;

        mockMvc.perform(delete("/ads/{id}", invalidId))
                .andExpect(status().isNotFound());
    }
}
