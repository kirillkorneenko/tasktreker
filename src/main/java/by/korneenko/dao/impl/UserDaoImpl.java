package by.korneenko.dao.impl;

import by.korneenko.beans.UserEntity;
import by.korneenko.dao.AbstractDao;
import by.korneenko.dao.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDao<Long, UserEntity> implements UserDao {
    @Override
    public UserEntity getByUserName(String name) {
        //**creating CriteriaBuilder**
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
        Root<UserEntity> userRoot = criteria.from(UserEntity.class);
        criteria.select(userRoot);

        //**Adding where clause**
        criteria.where(builder.equal(userRoot.get("name"), name));

        return getEntityManager().createQuery(criteria).getSingleResult();
    }

    @Override
    public List<UserEntity> findUserByNameOrSurname(String name, String surname) {
        //**creating CriteriaBuilder**
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
        Root<UserEntity> userRoot = criteria.from(UserEntity.class);
        criteria.select(userRoot);

        //**Adding where clause**
        criteria.where(builder.like(userRoot.get("name"), name));
        criteria.where(builder.like(userRoot.get("surname"),surname));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
