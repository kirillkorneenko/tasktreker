package by.korneenko.service;

import by.korneenko.beans.CommentEntity;
import by.korneenko.beans.TaskEntity;

import java.util.List;

public interface CommentService {
    List<CommentEntity> getCommentsByTask(TaskEntity taskEntity);
    void createComment(CommentEntity comment);
    void deleteComment(CommentEntity comment);
    void updateComment(CommentEntity comment);
}
