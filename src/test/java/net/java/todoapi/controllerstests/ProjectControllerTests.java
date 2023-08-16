package net.java.todoapi.controllerstests;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.java.todoapi.controllers.ProjectController;
import net.java.todoapi.models.Project;
import net.java.todoapi.services.ProjectImpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.sql.Date;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)

public class ProjectControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProjectImpService projectService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testIfReturnResponseProjects() throws Exception {
        var project = new Project("1", "test", "test detail", true, new Date(System.currentTimeMillis()));
        var projects = new ArrayList<Project>();
        projects.add(project);
        when(projectService.getProjects()).thenReturn(projects);


        mockMvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result[0].id").value("1"))
                .andExpect(jsonPath("$.result.length()").value(1))
        ;
    }

    @Test
    public void testIfReturnResponseFoundProject() throws Exception {
        var project = new Project("1", "test", "test detail", true, new Date(System.currentTimeMillis()));
        when(projectService.getProjectById(project.getId())).thenReturn(project);


        mockMvc.perform(get("/{id}", project.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value("1"))
                .andExpect(jsonPath("$.result.title").value("test"))
        ;
    }

    @Test
    public void testIfReturnOneInResultResponse() throws Exception {
        // Mock projectService behavior
        var project = new Project("1", "test", "test detail", true, new Date(System.currentTimeMillis()));

        when(projectService.addProject(any())).thenReturn(1);


        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("1"))
                .andDo(print());
        ;
    }

    @Test
    public void testIfReturnUpdatedProject() throws Exception {
        var updatedProject = new Project("1", "updated title", "updated", true, new Date(System.currentTimeMillis()));

        when(projectService.updateProject(any())).thenReturn(updatedProject);

        mockMvc.perform(put("/")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatedProject)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value("1"))
                .andExpect(jsonPath("$.result.title").value("updated title"))
                .andExpect(jsonPath("$.result.details").value("updated"))
                .andDo(print());
        ;
    }

    @Test
    public void testIfDeleteReturnOne() throws Exception {
        var project = new Project("1", "test", "test detail", true, new Date(System.currentTimeMillis()));
        when(projectService.deleteProject(project.getId())).thenReturn(1);


        mockMvc.perform(delete("/{id}", project.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("1"))
                .andDo(print());
        ;
    }






}
