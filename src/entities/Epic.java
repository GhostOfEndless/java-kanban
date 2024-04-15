package entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {

    final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public Epic( String name, String description) {
        super(description, name);
    }

    void addSubtask(Subtask newSubtask) {
        subtasks.putIfAbsent(newSubtask.getId(), newSubtask);
    }

    void updateSubtask(Subtask newSubtask) {
        if (subtasks.containsKey(newSubtask.getId())) {
            subtasks.put(newSubtask.getId(), newSubtask);
        }
    }

    ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    void removeSubtask(Integer id) {
        subtasks.remove(id);
    }

    void updateStatus() {
        boolean done = true;
        boolean inProgress = false;

        for (Subtask subtask: subtasks.values()) {
            if (subtask.getStatus() != Status.DONE) {
                done = false;
                if (subtask.getStatus() == Status.IN_PROGRESS) {
                    inProgress = true;
                }
            }
        }

        if (done) {
            status = Status.DONE;
        } else if (inProgress) {
            status = Status.IN_PROGRESS;
        } else {
            status = Status.NEW;
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtasks=" + subtasks +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
