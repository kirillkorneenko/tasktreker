package by.korneenko.service;

import by.korneenko.beans.ProjectEntity;
import by.korneenko.beans.UserEntity;

import java.util.List;

public interface ProjectService {

    ProjectEntity createProject(String name, String text);
    List<ProjectEntity> getProjectsByUserCreated ();
    List<ProjectEntity> getProjectByUser();
    void setDeveloperFromProject(Long id, String name);
}
