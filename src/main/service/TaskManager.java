package main.service;

import main.entities.Epic;
import main.entities.Subtask;
import main.entities.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    List<Subtask> getAllSubtasks();

    void removeAllTasks();

    void removeAllEpics();

    void removeAllSubtasks();

    Task getTaskById(Integer id);

    Epic getEpicById(Integer id);

    Subtask getSubtaskById(Integer id);

    Task createTask(Task newTask);

    Epic createEpic(Epic newEpic);

    Subtask createSubtask(Subtask newSubtask);

    Task updateTask(Task newTask);

    Epic updateEpic(Epic newEpic);

    Subtask updateSubtask(Subtask newSubtask);

    List<Subtask> getAllSubtasksOfEpic(Epic epic);

    void removeTaskById(Integer id);

    void removeEpicById(Integer id);

    void removeSubtaskById(Integer id);

    List<Task> getHistory();
}
