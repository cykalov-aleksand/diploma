package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.model.CreateOrUpdateComment;
import ru.skypro.homework.model.dto.Comment;
import ru.skypro.homework.model.dto.Comments;
@Service
public class CommentService {
        public Comments getCommentsOnAd(int id) {
        return null;
    }

       public Comment addCommentToAd(int id, String text) {
        return null;
    }


    public Void deleteCommentToAdId(int adId, int commentId) {
        return null;
    }

        public Comment updatingComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment) {
        return null;
    }
}
