package main.service;

import main.entities.Epic;
import main.entities.Subtask;
import main.entities.Task;
import main.util.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HistoryManager historyManager = Managers.getDefaultHistory();
    private Integer idCounter = 0;

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public void removeAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    @Override
    public void removeAllSubtasks() {
        for (Epic epic: epics.values()) {
            epic.removeAllSubtasks();
            epic.updateStatus();
        }
        subtasks.clear();
    }

    @Override
    public Task getTaskById(Integer id) {
        Task task = tasks.get(id);
        historyManager.add(task);
        return new Task(task);
    }

    @Override
    public Epic getEpicById(Integer id) {
        Epic epic = epics.get(id);
        historyManager.add(epic);
        return new Epic(epic);
    }

    @Override
    public Subtask getSubtaskById(Integer id) {
        Subtask subtask = subtasks.get(id);
        historyManager.add(subtask);
        return new Subtask(subtask);
    }

    @Override
    public Task createTask(Task newTask) {
        newTask.setId(idCounter++);
        tasks.put(newTask.getId(), new Task(newTask));
        return newTask;
    }

    @Override
    public Epic createEpic(Epic newEpic) {
        newEpic.setId(idCounter++);
        epics.put(newEpic.getId(), new Epic(newEpic));
        return newEpic;
    }

    @Override
    public Subtask createSubtask(Subtask newSubtask) {
        // проверка на корректность epicId
        if (epics.containsKey(newSubtask.getEpicId())) {
            newSubtask.setId(idCounter++);
            subtasks.put(newSubtask.getId(), new Subtask(newSubtask));
            Epic epic = epics.get(newSubtask.getEpicId());
            epic.addSubtask(subtasks.get(newSubtask.getId()));
            epic.updateStatus();
        }

        return newSubtask;
    }

    @Override
    public Task updateTask(Task newTask) {
        if (tasks.containsKey(newTask.getId())) {
            tasks.put(newTask.getId(), new Task(newTask));
        }

        return newTask;
    }

    @Override
    public Epic updateEpic(Epic newEpic) {
        // обновить можно только имя и описание
        if (epics.containsKey(newEpic.getId())) {
            Epic epic = epics.get(newEpic.getId());
            epic.setName(newEpic.getName());
            epic.setDescription(newEpic.getDescription());

            return new Epic(epic);
        }

        return newEpic;
    }

    @Override
    public Subtask updateSubtask(Subtask newSubtask) {
        if (subtasks.containsKey(newSubtask.getId()) &&
                epics.containsKey(newSubtask.getEpicId())) {
            subtasks.put(newSubtask.getId(), new Subtask(newSubtask));
            Epic epic = epics.get(newSubtask.getEpicId());
            epic.updateSubtask(subtasks.get(newSubtask.getId()));
            epic.updateStatus();
        }

        return newSubtask;
    }

    @Override
    public List<Subtask> getAllSubtasksOfEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            return epic.getSubtasks();
        }

        return new ArrayList<>();
    }

    @Override
    public void removeTaskById(Integer id) {
        tasks.remove(id);
    }


    @Override
    public void removeEpicById(Integer id) {
        if (epics.containsKey(id)) {
            for (Subtask subtask: epics.get(id).getSubtasks()) {
                subtasks.remove(subtask.getId());
            }
            epics.remove(id);
        }
    }

    @Override
    public void removeSubtaskById(Integer id) {
        if (subtasks.containsKey(id)) {
            Epic epic = getEpicById(subtasks.get(id).getEpicId());
            epic.removeSubtask(id);
            epic.updateStatus();
            subtasks.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}
