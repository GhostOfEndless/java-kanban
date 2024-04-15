package entities;

public class Subtask extends Task {

    private final Integer epicId;

    public Subtask(String name, String description, Integer epicId) {
        super(description, name);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epicId=" + epicId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
