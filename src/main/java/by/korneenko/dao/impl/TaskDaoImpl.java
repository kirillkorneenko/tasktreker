package by.korneenko.dao.impl;

import by.korneenko.beans.TaskEntity;
import by.korneenko.dao.AbstractDao;
import by.korneenko.dao.TaskDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class TaskDaoImpl extends AbstractDao<Long , TaskEntity> implements TaskDao {
    @Override
    public TaskEntity getByTaskName(String name) {
        //**creating CriteriaBuilder**
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<TaskEntity> criteria = builder.createQuery(TaskEntity.class);
        Root<TaskEntity> taskRoot = criteria.from(TaskEntity.class);
        criteria.select(taskRoot);

        //**Adding where clause**
        criteria.where(builder.equal(taskRoot.get("name"), name));

        return getEntityManager().createQuery(criteria).getSingleResult();
    }
}
