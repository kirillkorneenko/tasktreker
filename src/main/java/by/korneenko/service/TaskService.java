package by.korneenko.service;

import by.korneenko.beans.ProjectEntity;
import by.korneenko.beans.Task;
import by.korneenko.beans.TaskEntity;
import by.korneenko.beans.UserEntity;

import java.util.List;

public interface TaskService {
    List<TaskEntity> getTasksByProject(ProjectEntity project);
    TaskEntity createTask(Long id, String name, String text);
    void setDeveloperFromTask(Long id, String name);
    void redactTaskStatus(Long id, Task status);
    List<TaskEntity> getTaskByUser();
}
