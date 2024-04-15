package service;

import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private final Integer id;
    Status status;

    public Task(String description, String name) {
        this.id = TaskManager.getNewId();
        this.description = description;
        this.name = name;
        this.status = Status.NEW;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
