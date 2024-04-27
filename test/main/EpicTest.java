package main;

import main.entities.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EpicTest {

    @Test
    public void checkEpicsEqualWhenIdsEqual() {
        Epic epicOne = new Epic("", "");
        Epic epicTwo = new Epic("", "");
        epicOne.setId(1);
        epicTwo.setId(1);
        Assertions.assertEquals(epicOne, epicTwo);
    }
}
