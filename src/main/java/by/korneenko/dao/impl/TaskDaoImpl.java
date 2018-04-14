package by.korneenko.dao.impl;

import by.korneenko.beans.TaskEntity;
import by.korneenko.dao.AbstractDao;
import by.korneenko.dao.TaskDao;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDaoImpl extends AbstractDao<Long , TaskEntity> implements TaskDao {
}
