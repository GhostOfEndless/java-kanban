package main;


import main.entities.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void checkTasksEqualWhenIdsEqual() {
        Task taskOne = new Task("", "");
        Task taskTwo = new Task("", "");
        taskOne.setId(1);
        taskTwo.setId(1);
        Assertions.assertEquals(taskOne, taskTwo);
    }
}
