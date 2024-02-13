package app.ui.console;

import app.controller.ShowWaitingListController;
import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.WaitingUser.WaitingUserDTO;

import java.util.ArrayList;

public class ShowWaitingListUI implements Runnable{



    @Override
    public void run() {
        ShowWaitingListController controller = new ShowWaitingListController();
        try {
            ArrayList<WaitingUserDTO>  waitingUserDTO = controller.checkWaitingUserList();
            for (int i=0; i< waitingUserDTO.size(); i++){
                System.out.println(waitingUserDTO.get(i));
            }
        } catch (ListIsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }
}
