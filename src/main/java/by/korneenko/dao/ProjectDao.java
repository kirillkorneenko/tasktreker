package by.korneenko.dao;

import by.korneenko.beans.ProjectEntity;

public interface ProjectDao {
    void persist(ProjectEntity project);
    ProjectEntity getByKey(Long id);
    void update(ProjectEntity project);
    void delete(ProjectEntity project);
}
