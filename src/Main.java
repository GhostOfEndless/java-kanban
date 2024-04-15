import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        /*
        Менеджер задач в программе должен быть только один,
        поэтому уместнее всего реализовать в нём паттерн Singleton
         */
        TaskManager manager = TaskManager.getInstance();

    }
}
