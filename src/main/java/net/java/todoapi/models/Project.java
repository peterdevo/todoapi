package net.java.todoapi.models;

import java.sql.Date;

public class Project {
    String id;
    String title;
    String details;
    Boolean isCompleted;
    Date created;

    public Project(String id, String title, String details, Boolean isCompleted, Date created) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.isCompleted = isCompleted;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
