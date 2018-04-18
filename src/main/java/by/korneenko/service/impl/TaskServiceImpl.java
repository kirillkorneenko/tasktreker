package by.korneenko.service.impl;

import by.korneenko.beans.ProjectEntity;
import by.korneenko.beans.Task;
import by.korneenko.beans.TaskEntity;
import by.korneenko.beans.UserEntity;
import by.korneenko.dao.ProjectDao;
import by.korneenko.dao.TaskDao;
import by.korneenko.dao.UserDao;
import by.korneenko.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private TaskDao taskDao;
    private ProjectDao projectDao;
    private UserDao userDao;

    @Override
    @Transactional
    public List<TaskEntity> getTasksByProject(ProjectEntity project) {
        ProjectEntity projectEntity = projectDao.getByProjectName(project.getName());
        return projectEntity.getTasksById();
    }

    @Override
    @Transactional
    public TaskEntity createTask(Long id, String name, String text) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userDao.getByUserName(username);
        TaskEntity task = new TaskEntity();
        task.setName(name);
        task.setTaskText(text);
        task.setIdProject(id);
        task.getUserById().add(user);
        taskDao.persist(task);
        return taskDao.getByTaskName(task.getName());
    }

    @Override
    @Transactional
    public void setDeveloperFromTask(Long id, String name) {
        TaskEntity task = taskDao.getByKey(id);
        UserEntity user = userDao.getByUserName(name);
        List<UserEntity> list = task.getUserById();
        list.add(user);
        task.setUserById(list);
        taskDao.update(task);
    }

    @Override
    @Transactional
    public void redactTaskStatus(Long id, Task status) {
        TaskEntity task = taskDao.getByKey(id);
        task.setStatus(status);
        taskDao.update(task);
    }

    @Override
    @Transactional
    public List<TaskEntity> getTaskByUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userDao.getByUserName(username);
        UserEntity userEntity = userDao.getByKey(user.getId());
        return userEntity.getTaskById();
    }

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
