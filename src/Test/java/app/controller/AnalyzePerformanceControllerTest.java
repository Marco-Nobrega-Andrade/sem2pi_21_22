package app.controller;

import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.User.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AnalyzePerformanceControllerTest {
    @Test
    void checkIfUsersListIsEmpty() {
        AnalyzePerformanceController controller = new AnalyzePerformanceController();
        ArrayList<User> userList  = new ArrayList<>();
        String expectedMessage = "UsersList";
        try {
            controller.checkIfUsersListIsEmpty(userList);
        } catch (ListIsEmptyException e) {
            assertTrue(expectedMessage.contains(e.getMessage()));
        }
    }
}