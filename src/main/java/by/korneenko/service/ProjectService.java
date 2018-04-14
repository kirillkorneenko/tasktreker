package by.korneenko.service;

import by.korneenko.beans.ProjectEntity;
import by.korneenko.beans.UserEntity;

import java.util.List;

public interface ProjectService {

    ProjectEntity createProject(ProjectEntity project);
    List<ProjectEntity> getProjectsByUserCreated (UserEntity user);
    List<ProjectEntity> getProjectByUser(UserEntity user);
    void setDeveloperFromProject(UserEntity user, ProjectEntity project);
}
