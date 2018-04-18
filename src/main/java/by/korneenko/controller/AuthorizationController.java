package by.korneenko.controller;

import by.korneenko.beans.CodeConfirmationEntity;
import by.korneenko.beans.UserEntity;
import by.korneenko.encryption.MD5;
import by.korneenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthorizationController {

    UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    String getSinglePage() {
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    String getRegistration() {
        return "registration";
    }

    @PostMapping(value = "/authorization")
    ResponseEntity<?> authorization(@RequestBody String login,@RequestBody String password){
        UserEntity userEntity = userService.getByUserName(login);
        if(userEntity== null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Неверный логин");
        }
        else {
            if(userEntity.getPassword().equals(MD5.getHash(password))){
                return  ResponseEntity.ok().body("true");

            }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Неверный пароль");
        }
    }


    @PostMapping(value = "/registration")
    ResponseEntity registration(@RequestBody UserEntity user,@RequestBody String role) {
            user = userService.createUser(user, role);
            if(user==null){
                return new ResponseEntity(HttpStatus.CONFLICT);
            }else return new ResponseEntity(HttpStatus.CREATED);
    }


    @GetMapping(value = "/confirmationOfRegistration?code={code}")
    public String confirmationOfRegistartion(@PathVariable String code){
        CodeConfirmationEntity codeConfirmation = userService.getCodeConfirmation(code);
        if (codeConfirmation == null) {
            return null;
        }
        UserEntity user = codeConfirmation.getUserByIdUser();
        user.setConfirmed(true);
        userService.updateUser(user);
        return "redirect:/";
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
