package app.ui.console;

import app.controller.ImportUsersController;
import app.ui.console.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ImportUsersUI implements Runnable {


    ImportUsersController theController = new ImportUsersController();

    public ImportUsersUI() {
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        boolean flag = false;


        List<String> users = new ArrayList<>();
        String path = Utils.readLineFromConsole("Type the path to the CSV file.");
        Path path1 = Paths.get(path);

        try (BufferedReader br = Files.newBufferedReader(path1, StandardCharsets.UTF_8)) {

            String line = null;

            while ((line = br.readLine()) != null) {
                users.add(line);
            }

            try {
                flag = theController.importUsers(users);
            } catch (IllegalArgumentException | ParseException e) {
                System.out.println(e);
            }
            if (!flag) {
                throw new IllegalArgumentException("Could not import users from CSV file.");
            } else {
                registerUsersCredentials();
                System.out.println("Imported users with success.");
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e);
        }


    }

    public void registerUsersCredentials() {
        List<String> credentials = theController.getUsersCredentials();
        for (String c : credentials) {
            String[] credentialsSplit = c.split(",");
            System.out.print(credentialsSplit[1] + "\t" + credentialsSplit[2]+"\n");
        }
    }


}
