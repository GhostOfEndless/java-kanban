package service;

import entities.Epic;
import entities.Subtask;
import entities.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    ArrayList<Task> getAllTasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<Subtask> getAllSubtasks();

    void removeAllTasks();

    void removeAllEpics();

    void removeAllSubtasks();

    Task getTaskById(Integer id);

    Epic getEpicById(Integer id);

    Subtask getSubtaskById(Integer id);

    void createTask(Task newTask);

    void createEpic(Epic newEpic);

    void createSubtask(Subtask newSubtask);

    void updateTask(Task newTask);

    void updateEpic(Epic newEpic);

    void updateSubtask(Subtask newSubtask);

    ArrayList<Subtask> getAllSubtasksOfEpic(Epic epic);

    void removeTaskById(Integer id);

    void removeEpicById(Integer id);

    void removeSubtaskById(Integer id);

    List<Task> getHistory();
}
