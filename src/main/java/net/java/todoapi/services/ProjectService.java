package net.java.todoapi.services;

import net.java.todoapi.cores.ResultHandler;
import net.java.todoapi.models.Project;
import net.java.todoapi.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public ResultHandler<?> getProjects() {
        return ResultHandler.success(projectRepository.getProjects());
    }

    public ResultHandler<?> getProjectById(int id) {
        return ResultHandler.success(projectRepository.getProjectById(id));
    }

    public ResultHandler<?> addProject(Project project) {
        var result = projectRepository.addProject(project) > 0;
        if (result) {
            return ResultHandler.success(new HashMap<>());
        }
        return ResultHandler.failure("Failed to add project");
    }

    public ResultHandler<?> updateProject(Project project) {
        var proj = projectRepository.getProjectById(project.getId());

        if (proj == null) {
            return null;
        }
        var result = projectRepository.updateProject(project) > 0;
        if (!result) {
            return ResultHandler.failure("Failed to update");
        }
        return ResultHandler.success(projectRepository.getProjectById(project.getId()));

    }

    public ResultHandler<?> deleteProject(int id) {
        var proj = projectRepository.getProjectById(id);
        if (proj == null) {
            return null;
        }
        var result = projectRepository.deleteUpdate(id) > 0;
        if (!result) {
            return ResultHandler.failure("Failed to delete");
        }
        return ResultHandler.success("Successfully deleted");

    }


}
