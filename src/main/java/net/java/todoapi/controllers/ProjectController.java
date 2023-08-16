package net.java.todoapi.controllers;

import net.java.todoapi.cores.ResponseHandler;
import net.java.todoapi.models.Project;
import net.java.todoapi.services.ProjectImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    @Autowired
    ProjectImpService projectService;

    @GetMapping
    public ResponseEntity<?> getProjects() {
        return ResponseHandler.handleResponse(projectService.getProjects(), "");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable String id) {
        return ResponseHandler.handleResponse(projectService.getProjectById(id), "project found");
    }

    @PostMapping
    public ResponseEntity<?> addProject(@RequestBody Project project) {
        return ResponseHandler.handleResponse(projectService.addProject(project), "Successfully added");
    }

    @PutMapping
    public ResponseEntity<?> updateProject(@RequestBody Project project) {
        return ResponseHandler.handleResponse(projectService.updateProject(project), "Successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable String id) {
        return ResponseHandler.handleResponse(projectService.deleteProject(id), "Successfully deleted");
    }
}
