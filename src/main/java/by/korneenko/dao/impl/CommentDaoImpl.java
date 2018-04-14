package by.korneenko.dao.impl;

import by.korneenko.beans.CommentEntity;
import by.korneenko.dao.AbstractDao;
import by.korneenko.dao.CodeConfimationDao;
import by.korneenko.dao.CommentDao;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl extends AbstractDao<Long, CommentEntity> implements CommentDao {

}
