package by.korneenko.dao.impl;

import by.korneenko.beans.ProjectEntity;
import by.korneenko.beans.UserEntity;
import by.korneenko.dao.AbstractDao;
import by.korneenko.dao.ProjectDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProjectDaoImpl extends AbstractDao<Long, ProjectEntity> implements ProjectDao {
    @Override
    public List<ProjectEntity> getProjectByUserCreated(UserEntity user) {

        //**creating CriteriaBuilder**
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<ProjectEntity> criteria = builder.createQuery(ProjectEntity.class);
        Root<ProjectEntity> projectRoot = criteria.from(ProjectEntity.class);
        criteria.select(projectRoot);

        //**Adding where clause**
        criteria.where(builder.equal(projectRoot.get("idUserCreate"), user.getId()));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public ProjectEntity getByProjectName(String name) {

        //**creating CriteriaBuilder**
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<ProjectEntity> criteria = builder.createQuery(ProjectEntity.class);
        Root<ProjectEntity> projectRoot = criteria.from(ProjectEntity.class);
        criteria.select(projectRoot);

        //**Adding where clause**
        criteria.where(builder.equal(projectRoot.get("name"), name));

        return getEntityManager().createQuery(criteria).getSingleResult();
    }
}
