package service;

import entities.Epic;
import entities.Subtask;
import entities.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private Integer idCounter = 0;

    private Integer getNewId() {
        return idCounter++;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void removeAllSubtasks() {
        for (Epic epic: epics.values()) {
            epic.removeAllSubtasks();
            epic.updateStatus();
        }
        subtasks.clear();
    }

    public Task getTaskById(Integer id) {
        return tasks.get(id);
    }

    public Epic getEpicById(Integer id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(Integer id) {
        return subtasks.get(id);
    }

    public void createTask(Task newTask) {
        // создаём задачу только в том случае, если пользователь не вызывал сам setId(Integer)
        if (newTask.getId() == null) {
            newTask.setId(getNewId());
            tasks.put(newTask.getId(), newTask);
        }
    }

    public void createEpic(Epic newEpic) {
        if (newEpic.getId() == null) {
            newEpic.setId(getNewId());
            epics.put(newEpic.getId(), newEpic);
        }
    }

    public void createSubtask(Subtask newSubtask) {
        // проверка на корректность epicId
        if (newSubtask.getId() == null && epics.containsKey(newSubtask.getEpicId())) {
            newSubtask.setId(getNewId());
            subtasks.put(newSubtask.getId(), newSubtask);
            Epic epic = epics.get(newSubtask.getEpicId());
            epic.addSubtask(newSubtask);
            epic.updateStatus();
        }
    }

    public void updateTask(Task newTask) {
        if (tasks.containsKey(newTask.getId())) {
            tasks.put(newTask.getId(), newTask);
        }
    }

    public void updateEpic(Epic newEpic) {
        // обновить можно только имя и описание
        if (epics.containsKey(newEpic.getId())) {
            Epic epic = epics.get(newEpic.getId());
            epic.setName(newEpic.getName());
            epic.setDescription(newEpic.getDescription());
        }
    }

    public void updateSubtask(Subtask newSubtask) {
        if (subtasks.containsKey(newSubtask.getId()) &&
                epics.containsKey(newSubtask.getEpicId())) {
            subtasks.put(newSubtask.getId(), newSubtask);
            Epic epic = epics.get(newSubtask.getEpicId());
            epic.updateSubtask(newSubtask);
            epic.updateStatus();
        }
    }

    public ArrayList<Subtask> getAllSubtasksOfEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            return epic.getSubtasks();
        }

        return new ArrayList<>();
    }

    public void removeTaskById(Integer id) {
        tasks.remove(id);
    }


    public void removeEpicById(Integer id) {
        if (epics.containsKey(id)) {
            for (Subtask subtask: epics.get(id).getSubtasks()) {
                subtasks.remove(subtask.getId());
            }
            epics.remove(id);
        }
    }

    public void removeSubtaskById(Integer id) {
        if (subtasks.containsKey(id)) {
            Epic epic = getEpicById(subtasks.get(id).getEpicId());
            epic.removeSubtask(id);
            epic.updateStatus();
            subtasks.remove(id);
        }
    }
}
