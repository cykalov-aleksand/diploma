package ru.skypro.homework.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
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
/*
    @Test
    @WithMockUser
    public void updatingCommentAuthorizedTest() throws Exception {
        CreateOrUpdateComment beforeChange = new CreateOrUpdateComment();
        beforeChange.setText("fdgdfgdfgdfgdfg");
        Comment afterChange = new Comment(2,"dsfsd","dfsfs",242424,1,"fgdfgdfg");
        Ad ad = new Ad(3, "sdfsd",1,"dfgdfgdfgdf", 23);
        JSONObject createObject = new org.json.JSONObject();
        createObject.put("text", "Комментарий");
         when(commentService.updatingComment(ad.getPk(), afterChange.getPk(), beforeChange)).thenReturn(afterChange);
        mockMvc.perform(MockMvcRequestBuilders.patch("/ads/{adId}/comments/{commentId}?adId=" + ad.getPk()
                                + "&commentId=" + afterChange.getPk(), "adId", "commentId")
                        .content(createObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }


    @Test
    public void updatingCommentNoAuthorizedTest() throws Exception {
        CreateOrUpdateComment beforeChange = new CreateOrUpdateComment();
        Comment afterChange = new Comment();
        Ad ad = new Ad();
        JSONObject createObject = new org.json.JSONObject();
        createObject.put("text", "Комментарий");
        when(commentService.updatingComment(ad.getPk(), afterChange.getPk(), beforeChange)).thenReturn(afterChange);
        mockMvc.perform(MockMvcRequestBuilders.patch("/ads/{adId}/comments/{commentId}?adId=" + ad.getPk()
                                + "&commentId=" + afterChange.getPk(), "adId", "commentId")
                        .content(createObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(401));
    }
    */

 }
