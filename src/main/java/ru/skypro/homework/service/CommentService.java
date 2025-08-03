package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

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
