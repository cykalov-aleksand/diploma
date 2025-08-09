package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;

import static java.lang.System.currentTimeMillis;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    UserRepository userRepository;

    public CommentService(UserRepository userRepository, AdRepository adRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
    }


    public Comments getCommentsOnAd(int id) {


        return null;
    }

    public ResponseEntity<Comment> addCommentToAd(int id, String text) {
        AdModel adModel = adRepository.findPkObject(id);
        if (adModel == null) {
            return ResponseEntity.status(403).build();
        }
        long time = currentTimeMillis();
        int author = adRepository.findAuthorToPk(id);
        UserModel userModel = userRepository.userModel(author);
        commentRepository.saveComment(id, time, text);
        return ResponseEntity.ok(new Comment(author, userModel.getImage(), userModel.getFirstName(), time,
                commentRepository.pkToCreateText(time, text), text));
    }

    public ResponseEntity<Void> deleteCommentToAdId(int adId, int commentId) {
        if (commentRepository.commentModel(commentId) == null) {
            return ResponseEntity.status(404).build();
        }
        if (commentRepository.findPkToAdPk(adId) == null) {
            return ResponseEntity.status(403).build();
        }
        commentRepository.deleteLine(commentId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Comment> updatingComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment) {
        List<Integer> listPkComment = commentRepository.findPkToAdPk(adId);
        if (commentRepository.commentModel(commentId) == null) {
            return ResponseEntity.status(404).build();
        }
        if (commentRepository.findPkToAdPk(adId) == null) {
            return ResponseEntity.status(403).build();
        }
               commentRepository.updatingCommentPk(createOrUpdateComment.getText(), adId);
                int author = adRepository.findAuthorToPk(adId);
                UserModel userModel = userRepository.userModel(author);
                CommentModel commentModel = commentRepository.commentModel(commentId);

                return ResponseEntity.ok(new Comment(author, userModel.getImage(), userModel.getFirstName(), commentModel.getCreatedAt(),
                        commentModel.getPk(), commentModel.getText()));

        }
    }
