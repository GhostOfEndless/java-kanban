package service;

import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {

    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public Epic(String description, String name) {
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
        return (ArrayList<Subtask>) subtasks.values();
    }

    public void removeSubtask(Integer id) {
        subtasks.remove(id);
    }

    public void updateStatus() {
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
}
