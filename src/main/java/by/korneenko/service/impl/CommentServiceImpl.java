package by.korneenko.service.impl;

import by.korneenko.beans.CommentEntity;
import by.korneenko.beans.TaskEntity;
import by.korneenko.dao.CommentDao;
import by.korneenko.dao.TaskDao;
import by.korneenko.dao.UserDao;
import by.korneenko.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private UserDao userDao;
    private CommentDao commentDao;
    private TaskDao taskDao;

    @Override
    @Transactional
    public List<CommentEntity> getCommentsByTask(Long id) {
        TaskEntity task = taskDao.getByKey(id);
        return task.getCommentsById();
    }

    @Override
    @Transactional
    public void createComment(Long id, String text) {
        CommentEntity comment = new CommentEntity();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        comment.setIdUser(userDao.getByUserName(username).getId());
        comment.setIdTask(id);
        comment.setText(text);
        commentDao.persist(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {
        CommentEntity comment =commentDao.getByKey(id);
        commentDao.delete(comment);
    }

    @Override
    @Transactional
    public void updateComment(Long id , String text) {
        CommentEntity comment =commentDao.getByKey(id);
        comment.setText(text);
        commentDao.update(comment);
    }

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
