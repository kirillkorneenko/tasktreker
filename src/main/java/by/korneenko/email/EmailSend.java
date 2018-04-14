package by.korneenko.email;

import by.korneenko.beans.CodeConfirmationEntity;
import by.korneenko.beans.UserEntity;
import by.korneenko.dao.CodeConfimationDao;
import by.korneenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.UUID;

public class EmailSend {
    private CodeConfimationDao codeConfimationDao;
    private UserService userService;
    private JavaMailSender mailSender;

public void sendEmailForConfirmed(UserEntity user){
    String code = UUID.randomUUID().toString();
    CodeConfirmationEntity codeConfirmation = new CodeConfirmationEntity();
    codeConfirmation.setUserByIdUser(user);
    codeConfirmation.setCode(code);

    String email1User = user.getEmail();
    String subject = "Регистрация выполнена";
    String links = "http://localhost:8080/confirmationOfRegistration.html?code=" + code;
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(email1User);
    email.setSubject(subject);
    email.setText("Для того чтобы подтвердить регистрацию пройдите по ссылке - http://localhost:8080" + links);
    mailSender.send(email);
}

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCodeConfimationDao(CodeConfimationDao codeConfimationDao) {
        this.codeConfimationDao = codeConfimationDao;
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
