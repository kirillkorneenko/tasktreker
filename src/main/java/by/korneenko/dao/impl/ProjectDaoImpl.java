package by.korneenko.dao.impl;

import by.korneenko.beans.ProjectEntity;
import by.korneenko.dao.AbstractDao;
import by.korneenko.dao.ProjectDao;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDaoImpl extends AbstractDao<Long, ProjectEntity> implements ProjectDao {
}
