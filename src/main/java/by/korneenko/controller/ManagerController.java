package by.korneenko.controller;

import by.korneenko.dao.ProjectDao;
import by.korneenko.service.ProjectService;
import by.korneenko.service.TaskService;
import by.korneenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ManagerController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "admin/projects/{name}/{description}", method = RequestMethod.POST)
    ResponseEntity addProject(@PathVariable String name, @PathVariable String description) {
        projectService.saveProject(name, description);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    ResponseEntity getUsersByName(@RequestParam String name, @RequestParam String lastName) {
        return new ResponseEntity(userService.getDevelopersbyfilter(name, lastName), HttpStatus.OK);

    }

    @RequestMapping(value = "/projects/{id}/{username}", method = RequestMethod.POST)
    ResponseEntity addProjectToUser(@PathVariable Long id, @PathVariable String username) throws CustomException {

        projectService.addProjectToUser(id, username);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks/{id}/{username}", method = RequestMethod.POST)
    ResponseEntity addTaskToUser(@PathVariable Long id, @PathVariable String username) throws CustomException {
        taskService.addDeveloperToTask(id, username);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity error(CustomException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
