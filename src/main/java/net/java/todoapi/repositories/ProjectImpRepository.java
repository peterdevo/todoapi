package net.java.todoapi.repositories;

import net.java.todoapi.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectImpRepository implements ProjectRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Project> getProjects() {
        var query = "SELECT * FROM project";
        return this.jdbcTemplate.query(query, (rs, row) -> {
            return new Project(rs.getString("id"), rs.getString("title"),
                    rs.getString("details"), rs.getBoolean("isCompleted"),
                    rs.getDate("created"));
        });
    }

    public Project getProjectById(String id) {
        var query = "SELECT * FROM project WHERE id = ? ";
        List<Project> projects = jdbcTemplate.query(query, new Object[]{id}, (rs, row) -> {
            return new Project(rs.getString("id"), rs.getString("title"),
                    rs.getString("details"), rs.getBoolean("isCompleted"),
                    rs.getDate("created"));
        });

        if (projects.isEmpty()) {
            return null;
        }
        return projects.get(0);
    }

    public int addProject(Project project) {
        var query = "INSERT INTO project (id,title,details,isCompleted,created) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(query, project.getId(), project.getTitle(), project.getDetails(), project.getIsCompleted(), project.getCreated());
    }

    public int updateProject(Project project) {
        var query = "UPDATE project SET title = ?, details = ?, isCompleted = ?, created = ? WHERE id = ?";
        return jdbcTemplate.update(query, project.getTitle(), project.getDetails(), project.getIsCompleted(), project.getCreated(), project.getId());
    }

    public int deleteProject(String id) {
        var query = "DELETE FROM project WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }



}
