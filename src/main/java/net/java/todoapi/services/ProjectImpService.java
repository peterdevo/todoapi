package net.java.todoapi.services;

import net.java.todoapi.cores.exceptions.ProjectBadRequestException;
import net.java.todoapi.cores.exceptions.ProjectNotFoundException;
import net.java.todoapi.models.Project;
import net.java.todoapi.repositories.ProjectImpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectImpService implements ProjectService {


    private final ProjectImpRepository projectImpRepository;

    @Autowired
    public ProjectImpService(ProjectImpRepository projectRepository) {
        this.projectImpRepository = projectRepository;
    }

    public List<Project> getProjects() {
        return projectImpRepository.getProjects();
    }

    public Project getProjectById(String id) {
        var result = projectImpRepository.getProjectById(id);
        if (result == null) {
            throw new ProjectNotFoundException("Project is not found");
        }
        return result;
    }


    public Integer addProject(Project project) {
        var result = projectImpRepository.addProject(project);
        if (!(result > 0)) {
            throw new ProjectBadRequestException("Unsuccessfully deleted");
        }
        return result;
    }

    public Project updateProject(Project project) {
        var proj = projectImpRepository.getProjectById(project.getId());

        if (proj == null) {
            throw new ProjectNotFoundException("Unsuccessfully update");
        }
        var result = projectImpRepository.updateProject(project);
        if (!(result > 0)) {
            throw new ProjectBadRequestException("Unsuccessfully update");
        }
        return projectImpRepository.getProjectById(project.getId());
    }

    public Integer deleteProject(String id) {
        var proj = projectImpRepository.getProjectById(id);
        if (proj == null) {
            throw new ProjectNotFoundException("Unsuccessfully deleted");
        }
        var result = projectImpRepository.deleteProject(proj.getId());
        if (!(result > 0)) {
            throw new ProjectBadRequestException("Unsuccessfully deleted");
        }
        return result;

    }


}
