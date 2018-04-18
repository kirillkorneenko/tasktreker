package by.korneenko.service.impl;

import by.korneenko.beans.ProjectEntity;
import by.korneenko.beans.UserEntity;
import by.korneenko.dao.ProjectDao;
import by.korneenko.dao.UserDao;
import by.korneenko.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private ProjectDao projectDao;
    private UserDao userDao;

    @Override
    @Transactional
    public ProjectEntity createProject(String name, String text) {
        ProjectEntity project = new ProjectEntity();
        project.setName(name);
        project.setDescription(text);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userDao.getByUserName(username);
        project.getUsersById().add(user);
        projectDao.persist(project);
        ProjectEntity projectEntity =projectDao.getByProjectName(project.getName());
        if(projectEntity == null) return null;
        else return projectEntity;
    }

    @Override
    @Transactional
    public List<ProjectEntity> getProjectsByUserCreated() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userDao.getByUserName(username);
        return projectDao.getProjectByUserCreated(user);
    }

    @Override
    @Transactional
    public List<ProjectEntity> getProjectByUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userDao.getByUserName(username);
        return  user.getProjectsById();
    }

    @Override
    @Transactional
    public void setDeveloperFromProject(Long id, String name) {
        UserEntity user = userDao.getByUserName(name);
        ProjectEntity  projectEntity =projectDao.getByKey(id);
        projectEntity.getUsersById().add(user);
        projectDao.update(projectEntity);
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
