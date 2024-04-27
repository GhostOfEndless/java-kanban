package main;

import main.entities.Subtask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubtaskTest {

    @Test
    public void checkSubtasksEqualWhenIdsEqual() {
        Subtask subtaskOne = new Subtask("", "", 0);
        Subtask subtaskTwo = new Subtask("", "", 0);
        subtaskOne.setId(1);
        subtaskTwo.setId(1);
        Assertions.assertEquals(subtaskOne, subtaskTwo);
    }
}
