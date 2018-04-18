package by.korneenko.controller;

import by.korneenko.service.ProjectService;
import by.korneenko.service.TaskService;
import by.korneenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/project")
    ResponseEntity addProject(@RequestBody String name, @RequestBody String text) {
        projectService.createProject(name, text);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/user?name={name}&lastName={lastName}")
    ResponseEntity getUsersByName(@PathVariable String name, @PathVariable String lastName) {
        return new ResponseEntity(userService.getDeveloperByNameOrSurname(name, lastName), HttpStatus.OK);

    }

    @PostMapping(value = "/projects")
    ResponseEntity addProjectToUser(@RequestBody Long id, @RequestBody String username) {
        projectService.setDeveloperFromProject(id, username);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/tasks")
    ResponseEntity addTaskToUser(@RequestBody Long id, @RequestBody String username) {
        taskService.setDeveloperFromTask(id, username);
        return new ResponseEntity(HttpStatus.OK);
    }



}
