package by.korneenko.dao.impl;

import by.korneenko.beans.RoleEntity;
import by.korneenko.dao.AbstractDao;
import by.korneenko.dao.RoleDao;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Long, RoleEntity> implements RoleDao {
}
