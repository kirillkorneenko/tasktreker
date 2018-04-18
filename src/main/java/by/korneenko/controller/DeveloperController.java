package by.korneenko.controller;

import by.korneenko.beans.ProjectEntity;
import by.korneenko.beans.Task;
import by.korneenko.beans.TaskEntity;
import by.korneenko.service.CommentService;
import by.korneenko.service.ProjectService;
import by.korneenko.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class DeveloperController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/task?{id}")
    ResponseEntity getTask(@PathVariable Long id) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(id);
        return new ResponseEntity(taskService.getTasksByProject(projectEntity), HttpStatus.OK);
    }

    @GetMapping(value = "/projects")
    ResponseEntity getProject() {
        return new ResponseEntity(projectService.getProjectByUser(), HttpStatus.OK);
    }

    @GetMapping(value = "/comment?{id}")
    ResponseEntity getComment(@PathVariable Long id) {
        return new ResponseEntity(commentService.getCommentsByTask(id), HttpStatus.OK);
    }

    @PutMapping(value = "/comment?{id}&{description}")
    ResponseEntity updateComment(@PathVariable Long id, @PathVariable String text) {
        commentService.updateComment(id, text);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/comment/{id}")
    ResponseEntity deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/comment")
    ResponseEntity createComment(@RequestBody Long id, @RequestBody String text) {
        commentService.createComment(id, text);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/task")
    ResponseEntity createTask(@RequestBody Long id, @RequestBody String name, @RequestBody String text) {
        taskService.createTask(id, name, text);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(value = "/tasks/status")
    ResponseEntity updateStatus(@RequestBody Long id, @RequestBody Task status) {
        taskService.redactTaskStatus(id, status);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/tasksByUser")
    ResponseEntity getTasksByUser() {
        return new ResponseEntity(taskService.getTaskByUser(), HttpStatus.OK);
    }

}
