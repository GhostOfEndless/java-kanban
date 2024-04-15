import entities.*;

public class Main {

    /*
        Менеджер задач в программе должен быть только один,
        поэтому уместнее всего реализовать в нём паттерн Singleton
         */
    private static final TaskManager manager = TaskManager.getInstance();

    public static void main(String[] args) {
        System.out.println("* Создание объектов *");

        SimpleTask taskOne = new SimpleTask("Task One", "Description for task one");
        SimpleTask taskTwo = new SimpleTask("Task Two", "Description for task two");
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
        subtaskTwo.setStatus(Status.IN_PROGRESS);
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
        System.out.println("Список эпиков: ");
        System.out.println(manager.getAllEpics());
        System.out.println("Список задач: ");
        System.out.println(manager.getAllTasks());
        System.out.println("Список подзадач: ");
        System.out.println(manager.getAllSubtasks());
    }
}
