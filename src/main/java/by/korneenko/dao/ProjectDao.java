package by.korneenko.dao;

import by.korneenko.beans.ProjectEntity;
import by.korneenko.beans.UserEntity;

import java.util.List;

public interface ProjectDao {
    List<ProjectEntity> getProjectByUserCreated(UserEntity user);
    ProjectEntity getByProjectName(String name);
    void persist(ProjectEntity project);
    ProjectEntity getByKey(Long id);
    void update(ProjectEntity project);
    void delete(ProjectEntity project);
}
