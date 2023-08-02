package net.java.todoapi.controllers;

import net.java.todoapi.cores.ResponseHandler;
import net.java.todoapi.models.Project;
import net.java.todoapi.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping
    public ResponseEntity<?> getProjects() {
        return ResponseHandler.handleResponse(projectService.getProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable int id) {
        return ResponseHandler.handleResponse(projectService.getProjectById(id));
    }

    @PostMapping
    public ResponseEntity<?> addProject(@RequestBody Project project) {
        return ResponseHandler.handleResponse(projectService.addProject(project));
    }

    @PutMapping
    public ResponseEntity<?> updateProject(@RequestBody Project project) {
        return ResponseHandler.handleResponse(projectService.updateProject(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable int id) {
        return ResponseHandler.handleResponse(projectService.deleteProject(id));
    }
}
