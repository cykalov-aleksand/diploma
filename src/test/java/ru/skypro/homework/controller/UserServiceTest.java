package ru.skypro.homework.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.model.UpdateUser;
import ru.skypro.homework.model.dto.User;
import ru.skypro.homework.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {
        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private UserService userService;
        @Test
        @WithMockUser
        public void getInformationAboutUserAuthorizedTest() throws Exception {
            when(userService.getInformationAboutUser()).thenReturn(new User());
            this.mockMvc.perform(MockMvcRequestBuilders.get("/users/me")
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is(200));
        }

    @Test
      public void getInformationAboutUserNoAuthorizedTest() throws Exception {
        when(userService.getInformationAboutUser()).thenReturn(new User());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/users/me")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(401));
    }
    @Test
    @WithMockUser
    public void updatingUserInformationAuthorizedTest() throws Exception {
        UpdateUser beforeChange=new UpdateUser();
        UpdateUser afterChange=new UpdateUser();
        JSONObject createObject = new org.json.JSONObject();
        createObject.put("firstName", "");
        createObject.put("lastName", "");
        createObject.put("phone", "+7 (695) 498-51-37");

        when(userService.updatingUserInformation(beforeChange)).thenReturn(afterChange);
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/me","")
                        .content(createObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }
    @Test
    @WithMockUser
    public void updatingUserInformationNoAuthorizedTest() throws Exception {
        UpdateUser beforeChange=new UpdateUser();
        UpdateUser afterChange=new UpdateUser();
        JSONObject createObject = new org.json.JSONObject();
        createObject.put("firstName", "");
        createObject.put("lastName", "");
        createObject.put("phone", "+7 (695) 498-51-37");

        when(userService.updatingUserInformation(beforeChange)).thenReturn(afterChange);
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/me","")
                        .content(createObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(401));
    }
}

