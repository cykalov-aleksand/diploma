package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.config.WebSecurityConfig;
import ru.skypro.homework.model.dto.Ads;
import ru.skypro.homework.model.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdsControllerTest {
   @Autowired
    private MockMvc mockMvc;
   @MockBean
   private AdService adService;
    @Test
@WithMockUser
    public void getInformationAboutAdTest()throws Exception{
    ExtendedAd user=new ExtendedAd();
   int id=1;

    when(adService.getInformationAboutAd(id)).thenReturn(user);
    mockMvc.perform(MockMvcRequestBuilders.get("/ads/{id}",id)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().is(200));
}

     @Test
    @WithMockUser
    public void deleteAdTest()throws Exception{
        Ads user=new Ads();
        when(adService.getAllService()).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads" )
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }
}

