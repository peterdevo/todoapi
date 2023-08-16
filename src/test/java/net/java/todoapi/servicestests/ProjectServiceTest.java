package net.java.todoapi.servicestests;

import net.java.todoapi.cores.exceptions.ProjectBadRequestException;
import net.java.todoapi.cores.exceptions.ProjectNotFoundException;
import net.java.todoapi.models.Project;
import net.java.todoapi.repositories.ProjectImpRepository;
import net.java.todoapi.services.ProjectImpService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ProjectServiceTest {
    @Mock
    private ProjectImpRepository projectRepository;

    @InjectMocks
    private ProjectImpService projectService;


    @Test
    public void testAddProject_ifReturnOne() {
        Project project = new Project(UUID.randomUUID().toString(), "test", "test detail", true, new Date(System.currentTimeMillis()));
        when(projectRepository.addProject(project)).thenReturn(1);

        var result = projectService.addProject(project);

        assertEquals(1, result);
    }

    @Test
    public void testAddProject_ifThrowBadRequestException() {
        Project project = new Project(null, "test", "test detail", true, new Date(System.currentTimeMillis()));
        when(projectRepository.addProject(project)).thenThrow(new ProjectBadRequestException("bad request"));

        assertThrows(ProjectBadRequestException.class, () -> projectService.addProject(project));


    }


    @Test
    public void testGetProjects_ifReturnOne() {
        Project project = new Project(UUID.randomUUID().toString(), "test", "test detail", true, new Date(System.currentTimeMillis()));
        var projects = new ArrayList<Project>();
        projects.add(project);
        when(projectRepository.getProjects()).thenReturn(projects);

        var result = projectService.getProjects();
        assertNotNull(result);

        assertTrue(result.size() > 0);

    }

    @Test
    public void testGetProjects_ifReturnEmptyList() {

        var projects = new ArrayList<Project>();
        when(projectRepository.getProjects()).thenReturn(projects);

        var result = projectService.getProjects();
        assertEquals(projects, result);

    }

    @Test
    public void testGetProjectById_returnProjectObject() {
        Project project = new Project(UUID.randomUUID().toString(), "test", "test detail", true, new Date(System.currentTimeMillis()));
        when(projectRepository.getProjectById(project.getId())).thenReturn(project);

        var result = projectService.getProjectById(project.getId());

        assertEquals(project.getId(), result.getId());
    }

    @Test
    public void testGetProjectById_returnNotFoundException() {
        var id = "ABC";
        when(projectRepository.getProjectById(id)).thenThrow(new ProjectNotFoundException("not found"));
        assertThrows(ProjectNotFoundException.class, () -> projectService.getProjectById(id));
    }

    @Test
    public void testUpdateProject() {
        Project project = new Project(UUID.randomUUID().toString(), "testupdated", "test detail", true, new Date(System.currentTimeMillis()));
        when(projectRepository.getProjectById(project.getId())).thenReturn(project);
        when(projectRepository.updateProject(project)).thenReturn(1);

        var result = projectService.updateProject(project);

        assertTrue(project.getTitle().equals(result.getTitle()));

    }

    @Test
    public void testUpdateProject_returnBadException() {
        Project project = new Project(UUID.randomUUID().toString(), "testupdated", "test detail", true, new Date(System.currentTimeMillis()));
        when(projectRepository.getProjectById(project.getId())).thenReturn(project);
        when(projectRepository.updateProject(project)).thenThrow(new ProjectBadRequestException("bad request"));

        assertThrows(ProjectBadRequestException.class, () -> projectService.updateProject(project));
    }

    @Test
    public void testDeleteProject_returnOne() {
        Project project = new Project("ABC", "testupdated", "test detail", true, new Date(System.currentTimeMillis()));

        when(projectRepository.getProjectById(project.getId())).thenReturn(project);
        when(projectRepository.deleteProject(project.getId())).thenReturn(1);

        var result = projectService.deleteProject(project.getId());

        assertTrue(result == 1);

    }

    @Test
    public void testDeleteProject_returnBadRequestException() {
        Project project = new Project("ABC", "testupdated", "test detail", true, new Date(System.currentTimeMillis()));

        when(projectRepository.getProjectById(project.getId())).thenReturn(project);
        when(projectRepository.deleteProject(project.getId())).thenThrow(new ProjectBadRequestException("Bad request"));


        assertThrows(ProjectBadRequestException.class, () -> projectService.deleteProject(project.getId()));


    }


}
