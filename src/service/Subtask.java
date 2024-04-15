package service;

public class Subtask extends Task {

    private final Integer epicId;

    public Subtask(String description, String name, Integer epicId) {
        super(description, name);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }
}
