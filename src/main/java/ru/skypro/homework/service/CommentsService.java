package ru.skypro.homework.service;

import ru.skypro.homework.model.dto.Comment;
import ru.skypro.homework.model.dto.Comments;
import ru.skypro.homework.model.CreateOrUpdateComment;

public interface CommentsService {
    Comments getCommentsOnAd(int id);
    Comment addCommentToAd(int id, String text);
    Void deleteCommentToAdId(int adId,int commentId);
    Comment updatingComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment);
}
