package by.korneenko.service;

import by.korneenko.beans.ProjectEntity;
import by.korneenko.beans.TaskEntity;
import by.korneenko.beans.UserEntity;

import java.util.List;

public interface TaskService {
    List<TaskEntity> getTasksByProject(ProjectEntity project);
    TaskEntity createTask(TaskEntity task);
    void setDeveloperFromTask(UserEntity user, TaskEntity task);
    void redactTaskStatus(TaskEntity task, Long status);
    List<TaskEntity> getTaskByUser(UserEntity user);
}
