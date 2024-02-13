package app.controller;

public class SerializationController {

    public boolean readFile(){
        return App.getInstance().getCompany().importSerialization();
    }

    public boolean writeFile(){
        return App.getInstance().getCompany().saveSerialization();
    }
}
