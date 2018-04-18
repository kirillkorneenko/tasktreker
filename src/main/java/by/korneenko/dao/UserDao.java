package by.korneenko.dao;

import by.korneenko.beans.UserEntity;

import java.util.List;

public interface UserDao {
    UserEntity getByUserName(String name);
    void persist(UserEntity user);
    UserEntity getByKey(Long id);
    void update(UserEntity user);
    void delete(UserEntity user);
    List<UserEntity> findUserByNameOrSurname(String name, String surname);
}
