package by.korneenko.service;

import by.korneenko.beans.CommentEntity;
import by.korneenko.beans.TaskEntity;

import java.util.List;

public interface CommentService {
    List<CommentEntity> getCommentsByTask(Long id);
    void createComment(Long id, String text);
    void deleteComment(Long id);
    void updateComment(Long id , String text);
}
