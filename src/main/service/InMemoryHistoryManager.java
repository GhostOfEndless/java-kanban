package main.service;

import main.entities.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int HISTORY_LENGTH = 10;

    private final List<Task> viewHistory = new LinkedList<>();

    @Override
    public void add(Task task) {
        if (task != null) {
            viewHistory.add(new Task(task));
            if (viewHistory.size() > HISTORY_LENGTH) {
                viewHistory.removeFirst();
            }
        }
    }

    @Override
    public List<Task> getHistory() {
        return deepCopyViewHistory();
    }

    private List<Task> deepCopyViewHistory() {
        List<Task> list = new ArrayList<>();

        for (Task task: viewHistory) {
            list.add(new Task(task));
        }

        return list;
    }
}
