package app.ui.console.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {

    private String path;
    private boolean appendToFile = false;

    public WriteFile(String filePath){
        path = filePath;
    }

    public WriteFile(String filePath, boolean appendValue){
        this.path = filePath;
        this.appendToFile = appendValue;
    }

    public void WriteToFile(String textline) throws IOException {

        FileWriter writer = new FileWriter(path,appendToFile);
            PrintWriter printWriter = new PrintWriter(writer);

            printWriter.printf("%s" + "%n", textline);

            printWriter.close();

    }
}
