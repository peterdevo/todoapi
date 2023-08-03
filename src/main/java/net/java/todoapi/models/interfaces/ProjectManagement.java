package net.java.todoapi.models.interfaces;

import net.java.todoapi.models.Project;

import java.util.List;

public interface ProjectManagement {
    List<Project> getProjects();

    Project getProjectById(String id);

    int addProject(Project project);

    int updateProject(Project project);

    int deleteProject(String id);
}
