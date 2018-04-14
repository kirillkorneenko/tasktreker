package by.korneenko.service;

import by.korneenko.beans.CodeConfirmationEntity;
import by.korneenko.beans.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity createUser(UserEntity user, String role);
    CodeConfirmationEntity getCodeConfirmation(String code);
    UserEntity updateUser(UserEntity user);
    UserEntity getByUserName(String name);
    List<UserEntity> getDeveloperByNameOrSurname(String name, String surname);
}
