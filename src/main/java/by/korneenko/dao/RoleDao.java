package by.korneenko.dao;

import by.korneenko.beans.RoleEntity;

public interface RoleDao {
    void persist(RoleEntity role);
    RoleEntity getByKey(Long id);
    void update(RoleEntity role);
    void delete(RoleEntity role);
}
