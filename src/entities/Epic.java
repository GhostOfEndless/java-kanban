package entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {

    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public Epic( String name, String description) {
        super(description, name);
    }

    public void addSubtask(Subtask newSubtask) {
        subtasks.putIfAbsent(newSubtask.getId(), newSubtask);
    }

    public void updateSubtask(Subtask newSubtask) {
        if (subtasks.containsKey(newSubtask.getId())) {
            subtasks.put(newSubtask.getId(), newSubtask);
        }
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void removeSubtask(Integer id) {
        subtasks.remove(id);
    }

    public void removeAllSubtasks() {
        subtasks.clear();
    }

    @Override
    public void setStatus(Status status) {}

    public void updateStatus() {
        // Если список подзадач пуст, то ставим статус NEW и ничего не проверяем
        if (subtasks.isEmpty()) {
            status = Status.NEW;
            return;
        }

        int countDone = 0;
        int countNew = 0;

        for (Subtask subtask: subtasks.values()) {
            if (subtask.getStatus() == Status.NEW) {
                countNew++;
            } else if (subtask.getStatus() == Status.DONE) {
                countDone++;
            }
        }

        if (countDone == subtasks.size()) {
            status = Status.DONE;
        } else if (countNew == subtasks.size()) {
            status = Status.NEW;
        } else {
            status = Status.IN_PROGRESS;
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtasksSize=" + subtasks.size() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
