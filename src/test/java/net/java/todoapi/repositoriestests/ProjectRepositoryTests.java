package net.java.todoapi.repositoriestests;


import net.java.todoapi.models.Project;
import net.java.todoapi.repositories.ProjectImpRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectRepositoryTests {

    @Autowired
    ProjectImpRepository projectRepository;

    @Test
    public void testIfProjectIsAdded() {
        Project project = new Project(UUID.randomUUID().toString(), "test", "add test", true, new Date(System.currentTimeMillis()));
        projectRepository.addProject(project);
        var foundProject = projectRepository.getProjectById(project.getId());

        assertEquals(project.getId(), foundProject.getId());
    }

    @Test
    public void testGetProjectById() {
        Project project = new Project(UUID.randomUUID().toString(), "test", "test detail", true, new Date(System.currentTimeMillis()));
        projectRepository.addProject(project);
        var foundProject = projectRepository.getProjectById(project.getId());

        assertEquals(project.getId(), foundProject.getId());
    }

    @Test
    public void testIfProjectIsUpdated() {
        Project project = new Project(UUID.randomUUID().toString(), "test", "test detail", true, new Date(System.currentTimeMillis()));
        projectRepository.addProject(project);
        var updatedProject = new Project(project.getId(), "update", project.getDetails(), project.getIsCompleted(), project.getCreated());
        var resultUpdate = projectRepository.updateProject(updatedProject);
        var foundProject = projectRepository.getProjectById(updatedProject.getId());

        assertTrue(resultUpdate > 0);
        assertEquals("update", foundProject.getTitle());
    }

    @Test
    public void testGetProjects() {
        Project project = new Project(UUID.randomUUID().toString(), "test", "test detail", true, new Date(System.currentTimeMillis()));
        projectRepository.addProject(project);
        var result = projectRepository.getProjects();

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }


    @Test
    public void testIfProjectIsDeleted() {
        Project project = new Project(UUID.randomUUID().toString(), "test", "test detail", true, new Date(System.currentTimeMillis()));
        projectRepository.addProject(project);
        var result = projectRepository.deleteProject(project.getId());
        var foundProject = projectRepository.getProjectById(project.getId());

        assertTrue(result > 0);
        assertNull(foundProject);
    }


}
