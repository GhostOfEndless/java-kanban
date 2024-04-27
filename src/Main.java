import entities.*;

import service.Managers;
import service.TaskManager;

public class Main {

    private static final TaskManager manager = Managers.getDefault();

    public static void main(String[] args) {
        System.out.println("* Создание объектов *");

        Task taskOne = new Task("Task One", "Description for task one");
        Task taskTwo = new Task("Task Two", "Description for task two");
        manager.createTask(taskOne);
        manager.createTask(taskTwo);

        Epic epicOne = new Epic("Epic one", "Description for epic one");
        manager.createEpic(epicOne);

        Subtask subtaskOne = new Subtask("Subtask one",
                "Description for subtask one", epicOne.getId());
        Subtask subtaskTwo = new Subtask("Subtask two",
                "Description for subtask two", epicOne.getId());
        manager.createSubtask(subtaskOne);
        manager.createSubtask(subtaskTwo);


        Epic epicTwo = new Epic("Epic two", "Description for epic two");
        manager.createEpic(epicTwo);

        Subtask subtaskThree = new Subtask("Subtask three",
                "Description for subtask three", epicTwo.getId());
        manager.createSubtask(subtaskThree);

        printTasks();
        // ******************************************
        System.out.println("* Изменение статусов *");

        taskOne.setStatus(Status.IN_PROGRESS);
        manager.updateTask(taskOne);
        taskTwo.setStatus(Status.DONE);
        manager.updateTask(taskTwo);

        subtaskOne.setStatus(Status.DONE);
        manager.updateSubtask(subtaskOne);
        subtaskTwo.setStatus(Status.NEW);
        manager.updateSubtask(subtaskTwo);
        subtaskThree.setStatus(Status.DONE);
        manager.updateSubtask(subtaskThree);

        printTasks();
        // *********************************************
        System.out.println("* Удаление задач *");

        manager.removeTaskById(taskOne.getId());
        manager.removeEpicById(epicOne.getId());

        printTasks();
    }

    private static void printTasks() {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);

            for (Subtask task : epic.getSubtasks()) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}
