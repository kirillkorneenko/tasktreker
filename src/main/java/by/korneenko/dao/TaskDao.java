package by.korneenko.dao;

import by.korneenko.beans.TaskEntity;

public interface TaskDao {

    void persist(TaskEntity task);
    TaskEntity getByKey(Long id);
    void update(TaskEntity task);
    void delete(TaskEntity task);
}
