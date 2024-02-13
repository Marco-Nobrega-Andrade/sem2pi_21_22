package app.controller;

import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenterStore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ActorCenterControllerTest {

    @Test
    void getActorCenter() {
       ActorCenterController controller= new ActorCenterController();
        try {
            controller.getActorCenter();
        } catch (ListIsEmptyException e) {
            assertNotNull(e);
        }
    }
}