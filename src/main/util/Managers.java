package main.util;

import main.service.HistoryManager;
import main.service.InMemoryHistoryManager;
import main.service.InMemoryTaskManager;
import main.service.TaskManager;

public class Managers {

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
