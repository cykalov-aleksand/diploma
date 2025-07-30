package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.model.dto.Comments;
import ru.skypro.homework.model.dto.ExtendedAd;
import ru.skypro.homework.service.CommentService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentsControllerTest {
                @Autowired
        private MockMvc mockMvc;
        @MockBean
        private CommentService commentService;
    @Test
    @WithMockUser
    public void getCommentsOnAdAuthorizedTest() throws Exception {
        int id = 1;
        when(commentService.getCommentsOnAd(id)).thenReturn(new Comments());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ads/{id}/comments?id=" + id, "id")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }
    @Test
       public void getCommentsOnAdNoAuthorizedTest() throws Exception {
        int id = 1;
        when(commentService.getCommentsOnAd(id)).thenReturn(new Comments());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ads/{id}/comments?id=" + id, "id")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(401));
    }

}
