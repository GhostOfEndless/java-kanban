package service;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private static TaskManager instance;
    private final HashMap<Integer, SimpleTask> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private static Integer idCounter = 0;

    private TaskManager() {
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public static Integer getNewId() {
        return idCounter++;
    }

    public ArrayList<SimpleTask> getAllTasks() {
        return (ArrayList<SimpleTask>) tasks.values();
    }

    public ArrayList<Epic> getAllEpics() {
        return (ArrayList<Epic>) epics.values();
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return (ArrayList<Subtask>) subtasks.values();
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void removeAllSubtasks() {
        subtasks.clear();
    }

    public SimpleTask getTaskById(Integer id) {
        return tasks.get(id);
    }

    public Epic getEpicById(Integer id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(Integer id) {
        return subtasks.get(id);
    }

    public void createTask(SimpleTask newTask) {
        tasks.putIfAbsent(newTask.getId(), newTask);
    }

    public void createEpic(Epic newEpic) {
        epics.putIfAbsent(newEpic.getId(), newEpic);
    }

    public void createSubtask(Subtask newSubtask) {
        // проверка на корректность epicId
        if (!subtasks.containsKey(newSubtask.getId()) &&
                epics.containsKey(newSubtask.getEpicId())) {
            subtasks.put(newSubtask.getId(), newSubtask);
            epics.get(newSubtask.getEpicId()).addSubtask(newSubtask);
        }
    }

    public void updateTask(SimpleTask newTask) {
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
            getEpicById(subtasks.get(id).getEpicId()).removeSubtask(id);
            subtasks.remove(id);
        }
    }
}
