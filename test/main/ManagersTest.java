package main;

import main.util.Managers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagersTest {

    @Test
    public void checkThatGetDefaultNotNull() {
        Assertions.assertNotNull(Managers.getDefault());
    }

    @Test
    public void checkThatGetDefaultHistoryNotNull() {
        Assertions.assertNotNull(Managers.getDefaultHistory());
    }
}
