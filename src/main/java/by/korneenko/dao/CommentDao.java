package by.korneenko.dao;

import by.korneenko.beans.CommentEntity;

public interface CommentDao {
    void persist(CommentEntity comment);
    CommentEntity getByKey(Long id);
    void update(CommentEntity comment);
    void delete(CommentEntity comment);
}
