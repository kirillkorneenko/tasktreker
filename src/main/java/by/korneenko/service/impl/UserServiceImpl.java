package by.korneenko.service.impl;

import by.korneenko.beans.CodeConfirmationEntity;
import by.korneenko.beans.UserEntity;
import by.korneenko.dao.CodeConfimationDao;
import by.korneenko.dao.RoleDao;
import by.korneenko.dao.UserDao;
import by.korneenko.email.EmailSend;
import by.korneenko.encryption.MD5;
import by.korneenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;
    private CodeConfimationDao codeDao;

    @Override
    @Transactional
    public UserEntity createUser(UserEntity user, String role) {
        if(role.equals("manager")){
            user.setRoleByIdRole(roleDao.getByKey(1L));
        }else{
            user.setRoleByIdRole(roleDao.getByKey(2L));
        }
        user.setConfirmed(false);
        user.setPassword(MD5.getHash(user.getPassword()));
        userDao.persist(user);
        UserEntity userRet = userDao.getByUserName(user.getName());
        if(userRet!=null){
            EmailSend emailSend = new EmailSend();
            emailSend.sendEmailForConfirmed(userRet);
            return userRet;
        }else return null;
    }

    @Override
    @Transactional
    public CodeConfirmationEntity getCodeConfirmation(String code) {
        return codeDao.getByCode(code);
    }

    @Override
    @Transactional
    public UserEntity updateUser(UserEntity user) {
        userDao.update(user);
        return userDao.getByUserName(user.getName());
    }

    @Override
    @Transactional
    public UserEntity getByUserName(String name) {
        return userDao.getByUserName(name);
    }

    @Override
    @Transactional
    public List<UserEntity> getDeveloperByNameOrSurname(String name, String surname) {
        return userDao.findUserByNameOrSurname(name,surname);
    }


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setCodeDao(CodeConfimationDao codeDao) {
        this.codeDao = codeDao;
    }
}
