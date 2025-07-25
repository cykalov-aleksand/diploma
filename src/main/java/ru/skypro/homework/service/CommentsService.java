package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentsService {
    Comments getCommentsOnAd(int id);
    Comment addCommentToAd(int id, String text);
    Void deleteCommentToAdId(int adId,int commentId);
    Comment updatingComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment);
}
